
import pywhatkit as wpp

msg = "Isto é um teste!!!!"
number = "+351962261741"

wpp.sendwhatmsg(number,msg,16,19)

print(wpp.show_history())


