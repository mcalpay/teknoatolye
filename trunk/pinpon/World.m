//
//  World.m
//  pinpon
//
//  Created by Murat Can ALPAY on 9/7/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "World.h"

#import <GLUT/glut.h>

@implementation World

@synthesize milis,boxes;

static World * world;

-(id) addVectorBox:(VectorBox *) vbox {
	if( boxes == nil) {
		boxes = [[NSMutableArray alloc] initWithCapacity:5];
	}
	
	[boxes addObject:vbox];
	return self;
}

+(id) world {
	if( world == nil) {
		world = [World alloc];
	}
	
	return world;
}

-(id) startTickingEveryMilis:(NSUInteger) _milis {
	milis = _milis;
	glutTimerFunc(milis,worldTick,1);
	return self;
}

void worldTick(int value) {
	World * world = [World world];
	
	int len = [world.boxes count];
	for( int i = 0; i < len; i++) {
		VectorBox * vbox =[world.boxes objectAtIndex:i];
		vbox.x += vbox.distance*cos(vbox.angle);
		vbox.y += vbox.distance*sin(vbox.angle);
	}
	
	glutPostRedisplay();
	glutTimerFunc(world.milis,worldTick,1);
}

@end
