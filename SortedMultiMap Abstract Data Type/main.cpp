#include <iostream>
#include "SortedMultiMap.h"
#include "SMMIterator.h"

#include <assert.h>
#include <exception>
#include <algorithm>
#include <vector>

using namespace std;

bool relation1(TKey cheie1, TKey cheie2) {
    if (cheie1 <= cheie2) {
        return true;
    }
    else {
        return false;
    }
}

bool rel(int a, int b)
{
    return a < b;
}

bool asc(TKey c1, TKey c2) {
    if (c1 <= c2) {
        return true;
    } else {
        return false;
    }
}

bool desc(TKey c1, TKey c2) {
    if (c1 >= c2) {
        return true;
    } else {
        return false;
    }
}

void testAll();
void testAll(){
    SortedMultiMap smm = SortedMultiMap(relation1);
    assert(smm.size() == 0);
    assert(smm.isEmpty());

    smm.add(1,2);
    smm.add(1,3);

    assert(smm.size() == 2);

    assert(!smm.isEmpty());
    vector<TValue> v= smm.search(1);
    assert(v.size()==2);
    v= smm.search(3);
    assert(v.size()==0);
    SMMIterator it = smm.iterator();
    it.first();
    while (it.valid()){
        TElem e = it.getCurrent();
        it.next();
    }

    assert(smm.remove(1, 2) == true);
    assert(smm.remove(1, 3) == true);
    assert(smm.remove(2, 1) == false);
    assert(smm.isEmpty());
}

void testCreate();
void testCreate() {
    SortedMultiMap smm = SortedMultiMap(asc);
    assert(smm.size() == 0);
    assert(smm.isEmpty());

    for (int i = 0; i < 10; i++) {
        vector<TValue> v= smm.search(i);
        assert(v.size()==0);
    }

    for (int i = -10; i < 10; i++) {
        vector<TValue> v= smm.search(i);
        assert(v.size()==0);
    }
}

void testSearch(Relation r);
void testSearch(Relation r) {
    SortedMultiMap smm = SortedMultiMap(r);
    int kMin = 0;
    int kMax = 10;
    for (int i = kMin; i <= kMax; i++) {
        smm.add(i, i + 1);
        smm.add(i, i + 2);
    }

    int intervalDim = 10;
    for (int i = kMin; i <= kMax; i++) {
        vector<TValue> v= smm.search(i);
        assert(v.size()==2);
    }

    for (int i = kMin - intervalDim; i < kMin; i++) {
        vector<TValue> v= smm.search(i);
        assert(v.size()==0);
    }
    for (int i = kMax + 1; i < kMax + intervalDim; i++) {
        vector<TValue> v= smm.search(i);
        assert(v.size()==0);
    }
}

void testSearch();
void testSearch() {
    testSearch(asc);
    testSearch(desc);
}

void populateSMMEmpty(SortedMultiMap& smm, int min, int max);
void populateSMMEmpty(SortedMultiMap& smm, int min, int max) {
    for (int i = min; i <= max; i++) {
        smm.add(i, i);
        if (i%2 ==0)
            smm.add(i, i+2);
    }
}

void testRemoveSearch(Relation r);
void testRemoveSearch(Relation r) {
    SortedMultiMap smm = SortedMultiMap(r);
    int min = 10;
    int max = 20;
    populateSMMEmpty(smm, min, max);

    for (int c = min; c <= max; c++) {
        assert(smm.remove(c, c+1) == false);
        if (c%2==0)
            assert(smm.remove(c,c) == true);
    }

    for (int c = min; c <= max; c++) {
        if (c%2==1){
            assert(smm.remove(c,c+1) == false);
            assert(smm.remove(c,c) == true);
        }
        else{
            assert(smm.remove(c,c+2) == true);
        }
    }
    assert(smm.size() == 0);
}

void testRemove();
void testRemove() {
    testRemoveSearch(asc);
    testRemoveSearch(desc);
}

vector<int> randomKeys(int kMin, int kMax) {
    vector<int> keys;
    for (int c = kMin; c <= kMax; c++) {
        keys.push_back(c);
    }
    int n = keys.size();
    for (int i = 0; i < n - 1; i++) {
        int j = i + rand() % (n - i);
        swap(keys[i], keys[j]);
    }
    return keys;
}

void testIterator(Relation r);
void testIterator(Relation r) {
    SortedMultiMap smm = SortedMultiMap(r);
    SMMIterator it = smm.iterator();
    assert(!it.valid());
    it.first();
    assert(!it.valid());
    int cMin = 100;
    int cMax = 300;
    vector<int> keys = randomKeys(cMin, cMax);
    int n = keys.size();
    for (int i = 0; i < n; i++) {
        smm.add(keys[i], 100);
        if (keys[i]%2==0)	{
            smm.add(keys[i], 200);
        }
    }

    SMMIterator itsmm = smm.iterator();
    assert(itsmm.valid());
    itsmm.first();
    assert(itsmm.valid());

    TKey kPrev = itsmm.getCurrent().first;

    itsmm.next();
    while (itsmm.valid()) {
        TKey k = itsmm.getCurrent().first;
        kPrev = k;
        itsmm.next();
    }
}

void testIterator();
void testIterator() {
    testIterator(asc);
    testIterator(desc);
}

void testRemoveKey();
void testRemoveKey() {
    SortedMultiMap smm = SortedMultiMap(asc);
    smm.add(1, 4);
    smm.add(1, 5);
    smm.add(2, 3);
    smm.add(2, 5);
    smm.add(2, 9);
    smm.add(3, 7);
    vector<TValue> a = smm.removeKey(1);
    assert(a.size()==2);
    vector<TValue> b = smm.removeKey(2);
    assert(b.size()==3);
    vector<TValue> c = smm.removeKey(3);
    assert(c.size()==1);
}

int main() {
    testAll();
    cout << "SHORT TEST PASSED\n";
    testCreate();
    cout << "CREATE TEST PASSED\n";
    testSearch();
    cout << "SEARCH TEST PASSED\n";
    testRemove();
    cout << "REMOVE TEST PASSED\n";
    testIterator();
    cout << "ITERATOR TEST PASSED\n";
    testRemoveKey();
    cout << "TEST REMOVE KEY PASSED\n";
    return 0;
}