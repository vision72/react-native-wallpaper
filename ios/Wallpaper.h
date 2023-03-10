
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNWallpaperSpec.h"

@interface Wallpaper : NSObject <NativeWallpaperSpec>
#else
#import <React/RCTBridgeModule.h>

@interface Wallpaper : NSObject <RCTBridgeModule>
#endif

@end
