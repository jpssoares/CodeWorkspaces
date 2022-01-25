import pygame as pg

def printPuzzle(matrix):
    pg.init()
    
    h = len(matrix)
    w = len(matrix[0])

    BLACK = pg.Color('black')
    WHITE = pg.Color('white')

    tile_size = 80
    colors = []
    color_selection = [pg.Color("red"), pg.Color("green"), pg.Color("orange"), pg.Color("blue"), pg.Color("yellow"), pg.Color("purple"), pg.Color("pink"), pg.Color("Black")]
    width, height = w*tile_size, h*tile_size

    screen = pg.display.set_mode((width, height))
    clock = pg.time.Clock()

    background = pg.Surface((width, height))

    for i in range(h):
        for j in range(w):
            colors.append(color_selection[matrix[i][j] - 1])
            
    colors_it = iter(colors)

    for y in range(0, height, tile_size):
        for x in range(0, width, tile_size):
            rect = (x, y, tile_size, tile_size)
            pg.draw.rect(background, next(colors_it), rect)

    game_exit = False
    while not game_exit:
        for event in pg.event.get():
            if event.type == pg.QUIT:
                game_exit = True

        screen.fill((60, 70, 90))
        screen.blit(background, (0, 0))

        pg.display.flip()
        clock.tick(30)

    pg.quit()