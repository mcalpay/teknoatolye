//
//  main.m
//  openglbir
//
//  Created by Murat Can ALPAY on 8/4/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

//#include <OpenGL/gl.h>		// Header File For The OpenGL32 Library
//#include <OpenGL/glu.h>		// Header File For The GLu32 Library
#include <GLUT/glut.h>		// Header File For The GLut Library
// GLUT is the opengl tool kit
#import "World.h"
#import "PlatformCallbacks.h"
#import "BarCallbacks.h"
#import "BallCallbacks.h"
#import "BaseCallbacks.h"

int main(int argc, char *argv[]) {	
	// Initialize GLUT
	// these parameters are for the underlying windowing system
	// like -display DISPLAY or specifiying where the window will be drawn
	// -geometry W x H + X + Y
	glutInit(&argc, argv);
	
	// initilialize the size and position of window
	glutInitWindowPosition(400,100);
	glutInitWindowSize(400,300);
	// a single buffered rgb dowindow
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	
	//Create a window with rendering context and everything else we need
	
	// window with title
	glutCreateWindow("Intro");
	
	// to go full screen
	// glutFullScreen();
	
	// set the clearing color black
	glClearColor(0.0,0.0,0.0,0.0);
	
	//Assign the two used Msg-routines
	
	// display call back for current window
	// needs one
	//BaseCallbacks * callBack = [[[CircleCallbacks alloc] init:200 y:150 r:10] retain];
	BaseCallbacks * w1 = [[BarCallbacks alloc] initWithRect:390 y1:10 w:10 h:280];
	BaseCallbacks * w2 = [[BarCallbacks alloc] initWithRect:0 y1:10 w:10 h:280];
	BaseCallbacks * w3 = [[BarCallbacks alloc] initWithRect:0 y1:0 w:400 h:10];
	
	BaseCallbacks * p = [[PlatformCallbacks alloc] initWithRect:100 y1:290 w:100 h:10 distance:10];
	BaseCallbacks * ball = [[BallCallbacks alloc] initWithRect:200 y1:150 w:20 h:20 dist:3.0 ang:M_PI*2 / 6];
	World * world = [World world];

	[world addVectorBox:p.box];
	[world addVectorBox:ball.box];
	[world addVectorBox:w1.box];
	[world addVectorBox:w2.box];
	[world addVectorBox:w3.box];
	
	[world startTickingEveryMilis:20];
	
	glutMainLoop();
	
	return 0;
}
