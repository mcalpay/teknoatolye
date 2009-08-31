//
//  VectorBox.h
//  openglbir
//
//  Created by Murat Can ALPAY on 8/24/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface VectorBox : NSObject {
	float x, y, xr, yr;
}

@property float x, y, xr, yr;

-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr;

@end
