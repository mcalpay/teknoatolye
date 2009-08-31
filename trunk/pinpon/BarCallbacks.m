//
//  BarCallbacks.m
//  openglbir
//
//  Created by Murat Can ALPAY on 8/30/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "BarCallbacks.h"

#import <GLUT/GLUT.h>

@implementation BarCallbacks

@synthesize box;

static NSMutableArray * callbacks;

-(void) display {
	
	// loads the identity matrix
	glLoadIdentity();
	
	// floating point red, green, blue values 
	// 0 to 1 probably but may change I guess 3if ?
	glColor3f(0.0,0.0,1.0);
	glRectf(box.x - box.xr, box.y - box.yr, box.x + box.xr, box.y + box.yr);
}

//- (id)initWithFrame:(CGRect)frame reuseIdentifier:(NSString *)reuseIdentifier
-(void) reshape:(int) x y:(int) y {
	if (y == 0 || x == 0) return;  //Nothing is visible then, so return
	//Set a new projection matrix
	// apply the matrix operations to projection matrix stack
	glMatrixMode(GL_PROJECTION);  
	glLoadIdentity();
	
	//Angle of view:40 degrees
	//Near clipping plane distance: 0.5
	//Far clipping plane distance: 20.0
	
	// define the perpective
	// fovy : field of view angle in the y direction
	// aspect : x / y
	// zNear : distance from the viewer to nearest clipping plane always positive
	// zFar : distance from the viewer to far clipping plane always positive
	//gluPerspective(50.0,(GLdouble)x/(GLdouble)y,-3.0,3.0);
	
	glOrtho(0, x, y, 0, 0, 1);
	
	
	glMatrixMode(GL_MODELVIEW);
	
	// lower rectangle x,y, width,height
	glViewport(0,0,x,y);  //Use the whole window for rendering
}


-(void) keyHandler:(unsigned char) key x:(int) x y:(int) y {
	NSLog(@"keyHandler:%c", key);	
}

void keyHandler(unsigned char key, int x, int y) {
	for( int i = 0; i < callbacks.count; i++) {
		BarCallbacks * cb = [callbacks objectAtIndex:i];
		[cb keyHandler:key x:x y:y];
	}
}

-(void) keyUp:(unsigned char) key x:(int) x y:(int) y {
	NSLog(@"Key Up:%c", key);
}

void keyUp(unsigned char key, int x, int y) {
	for( int i = 0; i < callbacks.count; i++) {
		BarCallbacks * cb = [callbacks objectAtIndex:i];
		[cb keyUp:key x:x y:y];
	}
}


void display() {
	glClear(GL_COLOR_BUFFER_BIT);
	for( int i = 0; i < callbacks.count; i++) {
		BarCallbacks * cb = [callbacks objectAtIndex:i];
		[cb display];
	}
	glFlush();
}

//- (id)initWithFrame:(CGRect)frame reuseIdentifier:(NSString *)reuseIdentifier
void reshape(int x, int y) {
	for( int i = 0; i < callbacks.count; i++) {
		BarCallbacks * cb = [callbacks objectAtIndex:i];
		[cb reshape:x y:y];
	}
}

-(id) init {
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(keyHandler);
	glutKeyboardUpFunc(keyUp);
	if(callbacks == nil) {
		callbacks = [[NSMutableArray alloc] initWithCapacity:3];
	}
	
	[callbacks addObject:self];
	return self;
}

-(id) initWithRect:(float) _x1 y1:(float) _y1 w:(float) _w h:(float) _h {
	box = [[VectorBox alloc] initWithX:(_x1 + (_w/2)) y:(_y1 + (_h/2)) xr:_w/2 yr:_h/2];
	[self init];
	return self;
}

@end
