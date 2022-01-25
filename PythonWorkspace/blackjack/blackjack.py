import random

class Blackjack:

    def __init__(self, numberOfDecks):
        self.player = []
        self.house = []
        self.playing = True
        self.deck = []

        count = numberOfDecks
        while(count>0):
            self.deck = self.deck + self.shuffledDeck()
            count-=1
        self.startGame()
        
    def startGame(self): 
        self.player.append(self.dealCard())
        self.house.append(self.dealCard())
        self.player.append(self.dealCard())
        self.house.append(self.dealCard())

    def shuffledDeck(self):
        # returns shuffled deck
        # suits is a set of 4 Unicode symbols: back spade and club,
        # and white diamond and heart 
        suits = {'\u2660', '\u2661', '\u2662', '\u2663'}
        ranks = {'2','3','4','5','6','7','8','9','10','J','Q','K','A'}
        deck = []

        # create deck of 52 cards
        for suit in suits:
            for rank in ranks:             # card is the concatenation
                deck.append(rank+' '+suit) # of suit and rank

        # shuffle the deck and return
        random.shuffle(deck)
        return deck



    def dealCard(self):
        mylen = len(self.deck)
        cardIndex = random.randint(0,mylen-1)
        card = self.deck[cardIndex]
        self.deck.remove(card)
        return card



    def total(self,hand):
        result = 0
        for card in hand:
            number = card[:1]
            value = self.calculateValue(number,result)
            result += value

        return result

    def calculateValue(self,char,result):
        if (char.isnumeric()):
            if (char!="1"):
                return int(char)
            else: return 10
        else:
            if(char == "A"):
                if (result > 10):
                    return 1
                else: return 11
            else: return 10

    def busted(self,hand):
        if (self.total(hand)>21):
            return True
        else: return False

    def compareHands(self):
        playerScore = self.total(self.player)
        houseScore = self.total(self.house)

        if (houseScore == playerScore):
            return "Tie"
        elif((not self.busted(self.house) and houseScore>playerScore) or self.busted(self.player)):
            return "Lose"
        else: return "Win"

    def printState(self):
        print("--------------------")
        print("House:")
        print(self.house)
        print("Score: " + str(self.total(self.house)))

        print("Player:")
        print(self.player)
        print("Score: " + str(self.total(self.player)))
    
    def hit(self):
        if(self.playing==True):
            self.player.append(self.dealCard())
            if(self.busted(self.player)):
                self.playing = False
        else:
            print("GAME OVER")
    
    def hold(self):
        if(self.playing==True):
            while(self.compareHands()=="Win" and self.busted(self.house)==False):
                self.house.append(self.dealCard())
                self.printState()
            self.playing = False
        else:
            print("GAME OVER")

    def endGame(self):
        print("--------------------")
        print("FINAL SCORE")    
        self.printState()
        print(self.compareHands())
