import kotlin.random.Random

fun main() {
    val hand1 = Hand()
    val hand2 = Hand()


    println("Player's turn!")
    hand1.initialDraw()
    do {
        println("Hit[1] or Stand[0]")
        var draw: Int = Integer.valueOf(readLine())
        if (draw == 1){
            hand1.drawPlayer()
            hand1.checkScore()
            if(hand1.sumOfCards() > 21) draw = 0
        }
    }
    while (draw == 1)
    if(hand1.sumOfCards() > 21) println("DEALER WINS!")

    else{
        println("Dealer's turn!")
        hand2.initialDraw()
        hand2.drawDealer()
        hand2.checkScore()
        if(hand2.sumOfCards() > 21) println("PLAYER WINS!")
        else if(hand1.sumOfCards() > hand2.sumOfCards()) println("PLAYER WINS!")
        else if(hand1.sumOfCards() < hand2.sumOfCards()) println("DEALER WINS!")
        else println("IT'S A DRAW!")
    }


}

class Card(var number: Int = Random.nextInt(2, 12)){
    override fun toString(): String {
        return "$number"
    }
}

class Hand(private var hand: MutableList<Card> = mutableListOf(Card(), Card()), private var numberOfCards: Int = 2){
    fun initialDraw(){
        for(card in hand){
            println("In hand: ${card.number}")
        }
    }
    fun drawPlayer(){
        numberOfCards++
        hand.add(Card())
        if((hand[numberOfCards-1]).number == 11 && sumOfCards() > 21){
            println("You drew an Ace(value of 1)")
            hand[numberOfCards-1].number = 1
        }
        else if((hand[numberOfCards-1]).number == 11 && sumOfCards() <= 21) println("You drew an Ace(value of 11)")
        else println("You drew ${hand[numberOfCards-1]}")

    }
    fun sumOfCards(): Int{
        var sum = 0
        for(card in hand) sum+=card.number
        return sum
    }

    fun checkScore(){
        if(sumOfCards() > 21) {
            println("Over 21")
        }
        else println("Score: ${sumOfCards()}")
    }

    fun drawDealer(){
        while(sumOfCards() <= 16) {
            hand.add(Card())
            numberOfCards++
            if((hand[numberOfCards-1]).number == 11 && sumOfCards() > 21){
                println("Dealer drew an Ace(value of 1)")
                hand[numberOfCards-1].number = 1
            }
            else if((hand[numberOfCards-1]).number == 11 && sumOfCards() <= 21) println("Dealer drew an Ace(value of 11)")
            else println("Delaler drew ${hand[numberOfCards-1]}")
        }
    }

}

