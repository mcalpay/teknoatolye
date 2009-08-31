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

#import "BarCallbacks.h"

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
	[[BarCallbacks alloc] initWithRect:390 y1:10 w:10 h:280];
	[[BarCallbacks alloc] initWithRect:0 y1:10 w:10 h:280];
	[[BarCallbacks alloc] initWithRect:0 y1:0 w:400 h:10];
	glutMainLoop();
	
	return 0;
}
