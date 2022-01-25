
def find_lowest_grade(scs):
    dummies = []
    lowest_grade = -1
    
    for i in range(len(scs)):
        if (lowest_grade == -1) or (scs[i][1] <= lowest_grade):
            if(lowest_grade == -1) or (scs[i][1] < lowest_grade):
                lowest_grade = scs[i][1]
                dummies = []
            dummies.append(i)
    return dummies

if __name__ == '__main__':
    scores = []
    for _ in range(int(input())):
        name = input()
        score = float(input())
        scores.append([name, score])
    
    low_indexes = find_lowest_grade(scores)
    low_indexes.reverse()
    if(len(scores) > len(low_indexes)):
        for j in range(len(low_indexes)):
            scores.pop(low_indexes[j])
    
    if(len(scores) > 0):
        low_indexes = find_lowest_grade(scores)
        names = []
        for low in low_indexes:
            names.append(scores[low][0])
        names.sort()
        for n in names:
            print (n)

    