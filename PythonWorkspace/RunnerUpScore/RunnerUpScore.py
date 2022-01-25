if __name__ == '__main__':
    n = int(input())
    arr = map(int, input().split())
    nums = list(arr)
    nums.sort()
    nums.reverse()
    
    for i in range(len(nums)):
        if((nums[i+1])!=nums[i]):
            print(nums[i+1])
            break
    
    