"""
Recursiv
"""


def valid(k):
    stanga = 0
    dreapta = 0;
    for ct in range(1, k + 1):
        if st[ct] == 0:
            dreapta += 1
        else:
            stanga += 1
    if (dreapta <= n // 2 and stanga <= dreapta):
        return True
    else:
        return False


def printt(k):
    sol = []
    for i in range(1, k + 1):
        if st[i] == 1:
            sol.append(")")
        else:
            sol.append("(")
    solutie = ''.join(sol)

    print('\n')
    print(solutie)


def backt(k):
    for i in range(0, 2):
        st[k] = i
        if valid(k) == True:
            if k == n:
                printt(k)
            else:
                backt(k + 1)


st = []
n = int(input("Introduceti n: "))
for i in range(1, 101): st.append(i)
backt(1)
