/*
Assignment No.: 1(A)
Assignment Name: Implementation of any 2 uninformed search methods with some application.
Class: BE			Div: B
Roll No.: 63		Batch: B3
*/

#include<iostream>
#include<conio.h>
#include<stdlib.h>
using namespace std;
int cost[10][10],i,j,k,n,stk[10],top,v,visit[10],visited[10];
 
main()
{
int m;
cout <<"enterno of vertices";
cin >> n;
cout <<"ente no of edges";
cin >> m;
cout <<"\nEDGES \n";
for(k=1;k<=m;k++)
{
cin >>i>>j;
cost[i][j]=1;
}
 
cout <<"enter initial vertex";
cin >>v;
cout <<"ORDER OF VISITED VERTICES";
cout << v <<" ";
visited[v]=1;
k=1;
while(k<n)
{
for(j=n;j>=1;j--)
if(cost[v][j]!=0 && visited[j]!=1 && visit[j]!=1)
{
visit[j]=1;
stk[top]=j;
top++;
}
v=stk[--top];
cout<<v << " ";
k++;
visit[v]=0; visited[v]=1;
}
}
/*
OUTPUT
enterno of vertices9
ente no of edges9
EDGES
1 2
2 3
2 6
1 5
1 4
4 7
5 7
7 8
8 9
enter initial vertex1
ORDER OF VISITED VERTICES1 2 3 6 4 7 8 9 5
*/