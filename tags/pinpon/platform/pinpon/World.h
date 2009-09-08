//
//  World.h
//  pinpon
//
//  Created by Murat Can ALPAY on 9/7/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "VectorBox.h"


@interface World : NSObject {
	NSMutableArray * boxes;
	NSUInteger milis;
}

@property (readonly) NSUInteger milis;
@property (readonly) NSMutableArray * boxes;

-(id) addVectorBox:(VectorBox *) vbox;

-(id) startTickingEveryMilis:(NSUInteger) milis;

+(id) world;

void worldTick(int value);

@end
