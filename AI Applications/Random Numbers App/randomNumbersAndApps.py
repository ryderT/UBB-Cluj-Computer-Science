# -*- coding: utf-8 -*-
"""
Created on Tue Feb 25 11:09:34 2020

@author: ardel
"""
import numpy as np
import matplotlib.pyplot as plt


#################################################MENU#################################################################################

def menu():
    while True:
        print("1)Random Numbers\n")
        print("2)Sudoku\n")
        print("3)Cryptarithmetic game\n")
        print("4)Geometric forms\n")
        print("0)Exit\n")
        cmd=int(input("Command:"))
        if cmd==1:
            randomNumbers()
            return
        elif cmd==2:
            sudoku()
        elif cmd==3:
            cryptarithmetic()
        elif cmd==4:
            geometric()
        elif cmd==0:
            return

###############################################RANDOM NUMBERS#########################################################################

def plotL(l):
    l = sorted(l)
    x = []
    y = []
    xnr = None 
    ynr = 0
    for i in l:
        if i != xnr:
            if xnr is not None:
                x.append(xnr)
                y.append(ynr)
            xnr = i
            ynr = 1
        else:
            ynr += 1
    x.append(xnr)
    y.append(ynr)
    plt.bar(x, y)
    plt.show()


def randomNumbers():
    print("1)Hypergeometric\n")
    print("2)Binomial\n")
    cmd=int(input("Choose:"))
    
    #Samples are drawn from a hypergeometric distribution with specified parameters, 
    #ngood (ways to make a good selection), 
    #nbad (ways to make a bad selection), 
    #and nsample = number of items sampled, which is less than or equal to the sum ngood + nbad.
    if cmd==1:
         ngood=int(input("good="))
         nbad=int(input("bad="))
         samples=int(input("samples="))
         size=int(input("size="))
         random=np.random.hypergeometric(ngood,nbad,samples,size)
         print(random)
         #plt.plot(random)
         plotL(random)
         #plt.hist(z)
         
         
    #Samples are drawn from a binomial distribution with specified parameters,
    #n trials and p probability of success where n an integer >= 0 and 
    #p is in the interval [0,1]. 
    #(n may be input as a float, but it is truncated to an integer in use)
    else:
        n = int(input("n="))
        p = float(input("p="))
        size = int(input("size="))
        random=np.random.binomial(n,p,size)
        print(random)
        plotL(random)
        #plt.plot(random)
        
        
   
    
###################################...SUDOKU...#######################################################################################

def checkTableColumns(l):
    for i in range(0,len(l)):
        check=[0 for x in range(len(l)+1)]
        for j in range(0, len(l[i])):
            if l[i][j]!= 0 and l[i][j] in check:
                return False
            else:
                check[l[i][j]]=l[i][j]
    return True

def checkTableLines(l):
    check=[0 for x in range(len(l)+1)]
    for i in range(len(l)):
        for j in range(len(l)):
            if l[j][i]!=0 and l[j][i] in check:
                return False
            else:
                check[l[j][i]]=l[j][i]
        check=[0 for x in range(len(l)+1)]
    return True

def checktable4(l):
    check=[]
    for i in range(0,2):
        for j in range(0,2):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    #print("A")
    check=[]
    for i in range(0,2):
        for j in range(2,4):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    
    check=[]
    for i in range(2,4):
        for j in range(0,2):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(2,4):
        for j in range(2,4):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    return True

def checktable9(l):
    check=[]
    for i in range(0,3):
        for j in range(0,3):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(0,3):
        for j in range(3,6):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(0,3):
        for j in range(6,9):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(3,6):
        for j in range(0,3):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(3,6):
        for j in range(3,6):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(3,6):
        for j in range(6,9):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])   
    check=[]
    for i in range(6,9):
        for j in range(0,3):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(6,9):
        for j in range(3,6):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])
    check=[]
    for i in range(6,9):
        for j in range(6,9):
            if l[i][j]!=0 and l[i][j] in check:
                return False
            else:
                check.append(l[i][j])   
    return True
    
def initSudoku():
    l1=[[0 for x in range(4)] for y in range(4)]
    l1[0][0]=3
    l1[0][3]=2
    l1[1][1]=1
    l1[1][2]=4
    l1[2][0]=1
    l1[2][1]=2
    l1[2][3]=4
    l1[3][1]=3
    l1[3][2]=2
    l1[3][3]=1
    
    l2=[[0 for x in range(10) ]for y in range(10)]
    l2[0][1]=2
    l2[0][3]=6
    l2[0][5]=8
    l2[1][0]=5
    l2[1][1]=8
    l2[1][5]=9
    l2[1][6]=7
    l2[2][2]=7
    l2[2][4]=4
    l2[2][7]=2
    l2[2][8]=8
    l2[3][0]=3
    l2[3][1]=7
    l2[3][3]=4
    l2[3][5]=1
    l2[4][0]=6
    l2[4][4]=8
    l2[4][8]=5
    l2[5][2]=8
    l2[5][5]=2
    l2[5][7]=1
    l2[5][8]=3
    l2[6][0]=8
    l2[6][2]=6
    l2[6][4]=2
    l2[6][6]=1
    l2[7][2]=9
    l2[7][3]=8
    l2[7][7]=3
    l2[7][8]=6
    l2[8][0]=7
    l2[8][3]=3
    l2[8][5]=6
    l2[8][7]=9
    
    return l1,l2
    

def sudoku():
    l1,l2=initSudoku()
    x=int(input("table 1 or 2:"))
    if x == 1:
        c=int(input("Number of attempts:"))
        for k in range(0,c):
            random = np.random.randint(1, high=4, size=6)
            for i in range(len(l1)):
                for j in range(len(l1[i])):
                    if l1[i][j] == 0:
                        l1[i][j] = random[-1]
                        random = random[:-1]
            if checktable4(l1) and checkTableLines(l1) and checkTableColumns(l1):
                print("Solution Found!\n")
                print(l1)
                break
            else:
                print("Attempt "+str(k+1)+" failed!\n")
    else:
        c=int(input("Number of attempts:"))
        for k in range(0,c):
            #random = np.random.random_integers(1, high=9)
            for i in range(len(l2)):
                for j in range(len(l2[i])):
                    if l2[i][j] == 0:
                        l2[i][j] = np.random.randint(1, high=9)
            if checktable4(l2) and checkTableLines(l2) and checkTableColumns(l2):
                print("Solution Found!\n")
                print(l2)
                break
            else:
                print("Attempt "+str(k+1)+" failed!\n")
        
            
################################################...CRYPTARITHMETIC...########################################################################

def sendMoreMoney(nr):
    #S
    #E
    #N
    #D
    #M
    #O
    #R
    #Y
    for i in range(nr):
        hexa=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        S=np.random.choice(hexa)
        hexa.remove(S)
        M=np.random.choice(hexa)
        hexa.remove(M)
        E=np.random.choice(hexa)
        hexa.remove(E)
        N=np.random.choice(hexa)
        hexa.remove(N)
        D=np.random.choice(hexa)
        hexa.remove(D)
        O=np.random.choice(hexa)
        hexa.remove(O)
        R=np.random.choice(hexa)
        hexa.remove(R)
        Y=np.random.choice(hexa)
        hexa.remove(Y)
        
        if S!=0 and M!=0:
            rem=0
            if D+E>15:
                yt=D+E-16
                rem=1
            else:
                yt=D+E
                rem=0
                
            if N+R>15:
                et=D+E+rem-16
                rem=1
            else:
                et=D+E+rem
                rem=0
            if et>15:
                rem=1
                et=et-15
            
            if E+O>15:
                nt=E+O+rem-16
                rem=1
            else:
                nt=E+O+rem
                rem=0
            if nt>15:
                rem=1
                nt=nt-16
                
            if S+M>15:
                ot=S+M+rem-16
                rem=1
            else:
                ot=S+M+rem
                rem=0
            if ot>15:
                rem=1
                ot=ot-15
            mt=rem
            
            if mt==M and ot==O and nt==N and et==E and yt==Y:
                print("Success!")
                break
            else:
                print("Attempt "+str(i+1)+" failed!")
                
        else:
            print("Attempt "+str(i+1)+" failed!")

hexa=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        
def takeACakeKate(nr):
    #T
    #A
    #K
    #E
    #C
    
    for i in range(nr):
        hexa=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        T=np.random.choice(hexa)
        hexa.remove(T)
        A=np.random.choice(hexa)
        hexa.remove(A)
        K=np.random.choice(hexa)
        hexa.remove(K)
        E=np.random.choice(hexa)
        hexa.remove(E)
        C=np.random.choice(hexa)
        hexa.remove(C)
        
        if T!=0 and C!=0 and K!=0 and A!=0:
            rem=0
            if E+E+A>15:
                et=A+E+E-16
                rem=1
            else:
                et=E+A+E
                rem=0
            if et>15:
                rem+=1
                et=et-16
            
            if K+K>15:
                tt=K+K+rem-16
                rem=1
            else:
                tt=K+K+rem
                rem=0
            
            
            if A+A>15:
                at=A+A+rem-16
                rem=1
            else:
                at=A+A+rem
                rem=0
            if at>15:
                rem=1
                at=at-16
                
            if T+C>15:
                kt=T+C+rem-16
                rem=1
            else:
                kt=T+C+rem
                rem=0
            if kt>15:
                rem=1
                kt=kt-15
            mt=rem
            if mt==0 and at==A and kt==K and et==E and tt==T:
                print("Success!")
                break
            else:
                print("Attempt "+str(i+1)+" failed!")
                
        else:
            print("Attempt "+str(i+1)+" failed!")
            
        
        

def neverDriveRide(nr):
    #N
    #E
    #V
    #R
    #D
    #I
        
    for i in range(nr):
        hexa=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        N=np.random.choice(hexa)
        hexa.remove(N)
        E=np.random.choice(hexa)
        hexa.remove(E)
        V=np.random.choice(hexa)
        hexa.remove(V)
        R=np.random.choice(hexa)
        hexa.remove(R)
        D=np.random.choice(hexa)
        hexa.remove(D)
        I=np.random.choice(hexa)
        hexa.remove(I)
        if R!=0 and D!=0 and N!=0: 
            RIDE=E*1+D*16+I*16*16+R*16*16*16
            DRIVE=E*1+V*16+I*16*16+R*16*16*16+D*16*16*16*16
            NEVER=R*1+E*16+V*16+16+E*16*16*16+N*16*16*16*16
            if NEVER-DRIVE==RIDE:
                print("Success!")
                break
            else:
                print("Attempt "+str(i+1)+" failed!")
        else:
            print("Attempt "+str(i+1)+" failed!")
        
        
        
    
    
    


def cryptarithmetic():
    print("1)SEND+MORE=MONEY")
    print("2)TAKE+A+CAKE+KATE")
    print("3)NEVER-DRIVE=RIDE")
    #print("Enter choice:")
    cmd=int(input("Enter choice:"))
    nr=int(input("Enter nr of trials:"))
    if cmd==1:
        sendMoreMoney(nr)
    elif cmd==2:
        takeACakeKate(nr)
    elif cmd==3:
        neverDriveRide(nr)
        

#########################################################...GEOMETRIC FORMS...##############################################################
        
        
def initBoardAndForms():
    board=[[0 for x in range(6)]for y in range(5)]
    board=np.array(([0,0,0,0,0,0],
                    [0,0,0,0,0,0],
                    [0,0,0,0,0,0],
                    [0,0,0,0,0,0],
                    [0,0,0,0,0,0]))
    form1=np.array(([1,1,1,1]))
    form2=np.array(([1,0,1],
                    [1,1,1]))
    form3=np.array(([1,0,0],
                    [1,1,1]))
    form4=np.array(([0,0,1],
                    [1,1,1]))
    form5=np.array(([0,1,0],
                    [1,1,1]))
    forms=[form1,form2,form3,form4,form5]
    return board,forms
def checkBoard(board):
    for i in range(0,5):
        for j in range(0,6):
            if board[i][j]>1:
                return False
    return True

def geometric():
    nr=int(input("Enter nr of trials:"))
    board,forms=initBoardAndForms()
    
    for k in range(nr):
        for l in range(5):
            lines=[0,1,2,3]
            lines2=[0,1,2,3,4]
            columns=[0,1,2,3]
            columns2=[0,1,2]
            
            #form=np.random.choice(forms)
            #forms.remove(form)
            form=forms[l]
            
            if len(form)==4:
                line=np.random.choice(lines2)
                column=np.random.choice(columns2)
                for i in range(column,column+4):
                    board[line][column]+=1
            else:
                line=np.random.choice(lines)
                column=np.random.choice(columns)
                k1=0
                k2=0
                for i in range(line,line+2):
                    k2=0
                    for j in range(column, column+3):
                        board[i][j]+=form[k1][k2]
                        k2+=1
                    k1+=1
        
        if checkBoard(board):
            print("Success!")
            break
        else:
            print("Attempt "+str(k+1)+" failed!")
                
                
                
 #####################################################################################################################################               
menu()