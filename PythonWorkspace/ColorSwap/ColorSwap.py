from PIL import Image
import numpy as np

im = Image.open('ColorSwap/test.png')
im = im.convert('RGBA')

data = np.array(im)   # "data" is a height x width x 4 numpy array
red, green, blue, alpha = data.T # Temporarily unpack the bands for readability

# Replace white with red... (leaves alpha values alone...)
icon = (red == 255) & (blue == 255) & (green == 255)
background = (red != 255) & (blue != 255) & (green != 255)
data[..., :-1][background.T] = (212, 175, 55) # Transpose back needed
data[..., :-1][icon.T] = (255, 0, 0)

im2 = Image.fromarray(data)
im2.show()
