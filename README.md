# MSCorpus
Weka's SMO classifier for beat gestures.
The training set is an annotated corpus of videos.
The considered features are:
- Regarding the video:
  - gestures (beat, semantic or absence of gesture)
- Regarding the transcription of the speech:
  - communicative dimensions:
    - thematicity
    - focalization
    - emphasis
    - perspective
  - surface syntax
  - Part of Speech
  
The corpus is available in Github (https://github.com/carlaTV/MSCorpus/blob/master/wekaTest/CorpusAligned_BinaryBalanced.arff) and in Zenodo (https://sandbox.zenodo.org/record/223199#.W1CdLtgzaqQ)

The java code with the classifier is an adaption of the codes found in https://www.cs.umb.edu/~ding/history/480_697_spring_2013/homework/WekaJavaAPITutorial.pdf and
https://stackoverflow.com/questions/33760145/weka-how-to-predict-new-unseen-instance-using-java-code.
Originally, several classifiers were tested using the Weka GUI. The Java code is adapted to make predictions with new sentences.

## How to run the classifier
Execute the code in MSCorpus/wekaTest/src/wekatesting.java

## Processing of the outputs
The output of the classifier implemented in Java can be processed with R using MSCorpus/ProcessOutputPredictor.R
