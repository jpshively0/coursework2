int cardCompare(String card1, String card2){
 
//7S = 7 of spades, H<C<D<S, the suit priority is checked first and then the number
  
//inatislise both card values as zero and final result
  int c1 = 0;
  int c2 = 0;
	

//split both cards into number string and suit string
String num1 = card1.substring(0, card1.length() - 1);
        String suit1 = card1.substring(card1.length() - 1);
        String num2 = card2.substring(0, card2.length() - 1);
        String suit2 = card2.substring(card2.length() - 1);


//convert number string to int 
	c1 += Integer.valueOf(num1);
	c2 += Integer.valueOf(num2);
 
//adding in values of suit
switch(suit1) {
  case "S":
    c1+= 400;
    break;
  case "D":
    c1+= 300;
    break;
  case "C":
	c1 += 200;
	 break;
  case "H":
	c1 +=100;
	 break;
  default:
    c1+= 0;
}
 
 switch(suit2) {
  case "S":
    c2+= 400;
    break;
  case "D":
    c2+= 300;
    break;
  case "C":
	c2 += 200;
	 break;
  case "H":
	c2 +=100;
	 break;
  default:
    c2+= 0;
}
 


//comparing values of card and returning
if(c1>c2){

 return 1;

	}
else if(c1<c2){

  return -1;
	}
else {

return 0;

	}

		} 
 
 
 
 
      






//cardCompare("4H", "3S")



ArrayList<String> bubbleSort(ArrayList<String> array){

  //USe bubble sort to sort cards from least to greatests
  
  
  int x = array.size();
  
  
  for(int i = 0; i<x-1; i++){
	for(int j = 0; j < x - i -1; j++){
		if(cardCompare(array.get(j), array.get(j+1)) > 0) {
	
		String temp = array.get(j); 
		array.set(j, array.get(j+1)); 
		array.set(j+1, temp);
	
	}
  
  
  }
  }
  return array;
}


ArrayList<String> mergeSort(ArrayList<String> array){
   int size = array.size();
        if (size <= 1) return array;

        int mid = size / 2;
        ArrayList<String> left = new ArrayList<>(array.subList(0, mid));
        ArrayList<String> right = new ArrayList<>(array.subList(mid, size));

        return merge(mergeSort(left), mergeSort(right));
    }

    ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right) {
        ArrayList<String> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (cardCompare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }





long measureBubbleSort(String filename) throws IOException{


String directoryPath = "C:/Users/jpshi/OneDrive/Desktop/Uni/IP/coursework2";
Path path = Paths.get(directoryPath, filename).toAbsolutePath();
System.out.println("Attempting to read file at: " + path);



long startTime = System.nanoTime();
List<String> lines = Files.readAllLines(path);
ArrayList<String> array = new ArrayList<>(lines);
bubbleSort(array); 
long stopTime = System.nanoTime(); 
long time = stopTime - startTime; 
return time;

 
 
}

long measureMergeSort(String filename) throws IOException{
String directoryPath = "C:/Users/jpshi/OneDrive/Desktop/Uni/IP/coursework2";
Path path = Paths.get(directoryPath, filename).toAbsolutePath();
System.out.println("Attempting to read file at: " + path);



long startTime = System.nanoTime();
List<String> lines = Files.readAllLines(path);
ArrayList<String> array = new ArrayList<>(lines);
mergeSort(array); 
long stopTime = System.nanoTime(); 
long time = stopTime - startTime; 
return time;
}

void sortComparison(String[] filenames) throws IOException{
  String outputPath = "C:/Users/jpshi/OneDrive/Desktop/Uni/IP/coursework2/sortComparison.csv";
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath))) {
        writer.write("Filename,BubbleSortTime,MergeSortTime\n");
        for (String filename : filenames) {
            long bubbleTime = measureBubbleSort(filename);
            long mergeTime = measureMergeSort(filename);
            writer.write(filename + "," + bubbleTime + "," + mergeTime + "\n");
        }
    }
    System.out.println("Results written to " + outputPath);

  
}
