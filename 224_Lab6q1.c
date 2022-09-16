#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
int csum;
void *runner(void *param);

int main (int argc, char *argv[]){
	//int n, temp=0;
	//for(n=0;n<100000000;n++){
		pthread_t tid;
    		pthread_attr_t attr;
    		pthread_attr_init(&attr);
    		pthread_create(&tid, &attr, runner, argv[1]);
    		int msum = 0, i;
    		for(i = 1; i <= atoi(argv[1]); i++){
			msum += i;
    		}
    		pthread_join(tid, NULL);
    		//if(temp != csum-msum){
    			printf("csum:%d - msum:%d = %d\n",csum,msum,csum-msum);
		//	temp = csum - msum;
   		//}
	//}
    return 0;
}
void *runner(void *param){
    int upper = atoi(param) * 2;
    int i;
    csum = 0;
    for (i = 1; i <= upper; i++){
        csum+=i;
    }
    pthread_exit(0);
}
