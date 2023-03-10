import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-wallpaper' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const Wallpaper = NativeModules.Wallpaper
  ? NativeModules.Wallpaper
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return Wallpaper.multiply(a, b);
}

export function setWallpaper(imageUrl: string): Promise<boolean> {
  return Wallpaper.setWallpaper(imageUrl);
}
