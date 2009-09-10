//
//  BaseCallbacks.h
//  pinpon
//
//  Created by Murat Can ALPAY on 9/10/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "VectorBox.h"


@interface BaseCallbacks : NSObject {
	VectorBox * box;
}

@property (readonly) VectorBox * box;

-(id) initWithRect:(float) _x1 y1:(float) _y1 w:(float) _w h:(float) _h;
-(id) initWithRect:(float) _x1 y1:(float) _y1 w:(float) _w h:(float) _h dist:(float) _d ang:(float) _a;
-(id) init;

@end
