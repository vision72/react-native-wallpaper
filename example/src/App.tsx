import * as React from 'react';

import { StyleSheet, View, Text, Pressable } from 'react-native';
import { multiply, setWallpaper } from 'react-native-wallpaper';

const SetWallPaperButton = () => {
  const onPress = () => {
    const wallpaper: string = `YOUR_IMAGE_URL_HERE`;
    setWallpaper(wallpaper);
  };

  return (
    <Pressable onPress={onPress}>
      <Text style={styles.exampleButton}>
        Click to invoke wallpaper native module!
      </Text>
    </Pressable>
  );
};

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    multiply(3, 7).then(setResult);
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <SetWallPaperButton />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  exampleButton: {
    color: '#841584',
  },
});
