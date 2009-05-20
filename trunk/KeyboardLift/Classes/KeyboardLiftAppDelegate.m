//
//  KeyboardLiftAppDelegate.m
//  KeyboardLift
//
//  Created by Murat Can ALPAY on 5/20/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "KeyboardLiftAppDelegate.h"
#import "KeyboardLiftViewController.h"

@implementation KeyboardLiftAppDelegate

@synthesize window;
@synthesize viewController;


- (void)applicationDidFinishLaunching:(UIApplication *)application {    
    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
