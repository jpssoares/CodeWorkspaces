from blackjack import Blackjack

if __name__ == '__main__':
    b1 = Blackjack(1)
    b1.printState()

    b2 = Blackjack(1)
    b2.printState()

    b2.hit()
    b2.printState()
    b2.hold()
    b2.endGame()