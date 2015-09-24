/*
Assignment No.: Elective 2 - D (Group A)
Assignment Name: Write a program using Scala/ Python/ C++ using Eclipse to correct the spelling of English paragraph.
Class: BE		Div: B
Roll No.: 63		Batch: B4
*/
#include <iostream>
#include <fstream>
#include <cstring>
#include<cstdlib>
using namespace std;

void makeLower (char word[]);
void linearSearch (char dictionary[][30], int size, char word[][30]);

int main ()
{
	char filename[30];
	char word[300][30];
	char dictionary[45427][30];
	ifstream fin;
	int index2=0;

	fin.open ("dictionary.txt");
	if (fin.fail())
	{
		cout << "FILE NOT FOUND\n";
		exit (0);
	}

	for (int index=0;!fin.eof();index++) //read in dictionary file one  //word by word
	{                                      //already alphabetized.
		fin.getline (dictionary[index],30); 
	}
	
	fin.close ();
	fin.clear();
	cout << "Enter the file name: ";
	cin >> filename;
	fin.open (filename);
	if (fin.fail())
	{	
		cout << "FILE NOT FOUND\n";
		exit (0);
	}	
	for (index2=0; !fin.eof(); index2++) //this is the file to be spell checked
	{		
		fin >> word[index2]; //WHY WILL THIS NOT READ ANYTHING IN!?!?
		makeLower (word[index2]); //to get all words to lower case
	}
	fin.close ();

	linearSearch (dictionary,index2-1, word);
	//just a simple linear search for now.
	

	return 0;
}

void makeLower (char word[])
{
	int length = strlen(word);
	for (int n=0; n < length; n++)
		word[n] = tolower(word[n]);
}

void linearSearch (char dictionary[][30], int size, char word[][30])
{
	bool found = false;
	
	for (int scan=0; scan < size; scan++)
	{
		for (int n=0; n < 45427; n++)
		{
			found=false;
			if (strcmp(dictionary[n],word[scan]) ==0)
			{
				found = true;	
				cout<<"\ncorrect\t"<<word[scan];
				break;
			}
		}
		if(found==false)
		{
			 cout << endl << "Misspelled word list: \n\n" << word[scan] << endl;	
		}
	}
	
}

/*		OUTPUT
student@siftworkstation:~$ g++ spellcheck.cpp
student@siftworkstation:~$ ./a.out
Enter the file name: a.txt

correct	country
correct	is
Misspelled word list: 

beatiful
student@siftworkstation:~$ 
*/
