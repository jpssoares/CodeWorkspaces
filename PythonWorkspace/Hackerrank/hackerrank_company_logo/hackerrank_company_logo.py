
#in decimal (ascii) a = 97 & z = 122
#ord(char c)
#chr(int x)

def get_max(alpha):
	num = alpha.index(max(alpha))
	print(chr(num + 97) + " " + str(max(alpha)))
	return num

def companylogo(string):
	alpha = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
	for l in string:
		alpha[ord(l) - 97] = alpha[ord(l) - 97] + 1
	for n in range(3):
		index = get_max(alpha)
		alpha[index] = 0
	

if __name__ == '__main__':
	string = input().lower()
	if not (string.isalpha()):
		print("ERROR")
	else:
		companylogo(string)

	
