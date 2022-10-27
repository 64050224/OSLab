all: goodCnt

goodCnt: Lab9_q1.o peterson.o
	gcc -o goodCnt Lab9_q1.o peterson.o

Lab9_q1.o: Lab9_q1.c
	gcc -c Lab9_q1.c

peterson.o: peterson.c
	gcc -c peterson.c

clean:
	rm -rf *.o goodCnt