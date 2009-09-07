//
//  VectorBox.h
//  openglbir
//
//  Created by Murat Can ALPAY on 8/24/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface VectorBox : NSObject {
	float x, y, xr, yr, distance, angle;
}

@property float x, y, xr, yr, distance, angle;

-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr;
-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr distance:(float) _d angle:(float) _a;

@end
