//
//  BaseViewController.h
//  KeyboardLift
//
//  Created by Murat Can ALPAY on 5/20/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface BaseViewController  : UIViewController <UITextFieldDelegate> {
	bool keyboardShown;
	
	UITextField * activeField;
	
	UIScrollView * scrollView;
	
	UIView * mainView;
}

@property (nonatomic, retain) IBOutlet UIScrollView * scrollView;
@property (nonatomic, retain) IBOutlet UIView * mainView;

@end

