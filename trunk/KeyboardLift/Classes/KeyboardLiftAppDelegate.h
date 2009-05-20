//
//  KeyboardLiftAppDelegate.h
//  KeyboardLift
//
//  Created by Murat Can ALPAY on 5/20/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class KeyboardLiftViewController;

@interface KeyboardLiftAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    KeyboardLiftViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet KeyboardLiftViewController *viewController;

@end

