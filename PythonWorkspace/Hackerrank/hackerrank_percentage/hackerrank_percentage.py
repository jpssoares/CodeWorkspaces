
if __name__ == '__main__':
    n = int(input())
    student_marks = {}
    for _ in range(n):
        name, *line = input().split()
        scores = list(map(float, line))
        student_marks[name] = scores
    query_name = input()

    student_results = student_marks[query_name]
    average_query = (student_results[0]+ student_results[1]+ student_results[2])/3
    print("%.2f" % average_query)