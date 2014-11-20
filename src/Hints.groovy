// Problem: Reading a line

// Let's say that you have a hand that totals 14...
cardCount = 14
println "You have " + cardCount

// Take user input until the user enters something valid ("h" or "s")
def text = ''
while(text != 'h' && text != 's')
{
   print "Hit or Stand [h | s]: "
   text = System.console().readLine()
}
println "Thanks... you entered: " + text

