from PIL import Image
from numpy import asarray


def sixBitToSevenBit(character):
    if 0x1 <= character <= 0x1F:
        return chr(character + 0x40)
    else:
        return chr(character)


def main():
    global message
    img = asarray(Image.open("decodeSteganography/img/www.jpg"))
    message = ""

    for x in range(len(img)):
        for y in range(len(img[x])):
            pixel = img[y][x]
            n = ((pixel[0] & 0b11) << 4) + ((pixel[1] & 0b11) << 2) + (pixel[2] & 0b11)
            if n == 0:
                return

            message += sixBitToSevenBit(n)


if __name__ == '__main__':
    global message
    main()
    print(message)