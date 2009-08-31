//
//  BarCallbacks.h
//  openglbir
//
//  Created by Murat Can ALPAY on 8/30/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "VectorBox.h"

@interface BarCallbacks : NSObject {
	
	VectorBox * box;

}

@property (readonly) VectorBox * box;

-(id) initWithRect:(float) _x1 y1:(float) _y1 w:(float) _w h:(float) _h;
-(id) init;

@end
