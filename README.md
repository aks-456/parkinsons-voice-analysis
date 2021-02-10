# Parkinsons-s-Voice-Analysis
An attempt to classify Parkinson's Disease based on the extraction of vocal features from patient recordings. 

<hr>

## Parkinson's Disease

Parkinson’s Disease (PD) is progressive nervous system disorder that leads to shaking, stiffness, and difficulty with walking, balance, and coordination. The disease may also affect one’s ability to walk and talk. People may also experience mental and behavioural changes, sleep problems, depression, memory difficulties and fatigue. 

PD can occur in both men and women, however it is more prevalent amongst men. A major risk factor is age, with most people developing the disease at age 60. 

Diagnosing PD is difficult and often very tedious, since doctors diagnose other diseases with similar symptoms first. If a patient tests negative for all other diseases, only then can doctors diagnose the patient with PD. 


## Parkinson's Diseases Diagnosis From Vocal Features

PD affects Biomedical Voice Measurements (Vocal features) in patients. Vocal features include:

* MDVP:Fo(Hz) - Average vocal fundamental frequency 
* MDVP:Fhi(Hz) - Maximum vocal fundamental frequency 
* MDVP:Flo(Hz) - Minimum vocal fundamental frequency 
* MDVP:Jitter(%),MDVP:Jitter(Abs),MDVP:RAP,MDVP:PPQ,Jitter:DDP - Several measures of variation in fundamental frequency 
* MDVP:Shimmer,MDVP:Shimmer(dB),Shimmer:APQ3,Shimmer:APQ5,MDVP:APQ,Shimmer:DDA - Several measures of variation in amplitude 
* NHR,HNR - Two measures of ratio of noise to tonal components in the voice 
* status - Health status of the subject (one) - Parkinson's, (zero) - healthy 
* RPDE,D2 - Two nonlinear dynamical complexity measures 
* DFA - Signal fractal scaling exponent 
* spread1,spread2,PPE - Three nonlinear measures of fundamental frequency variation 

In order to classify PD, Machine Learning (ML) algorithms can be used on the UCI ML Repository: Parkinson's Disease Dataset, found here [https://archive.ics.uci.edu/ml/datasets/parkinsons] (https://archive.ics.uci.edu/ml/datasets/parkinsons)

I used a number of different approaches to classify the data, which was arranged in terms of patients with PD and patients without PD, as well as their respective vocal features extracted from voice recordings. I separated the data into a 70-30 train-test split, and recieved the following accuracies for each approach:

* Decision Tree Classifier - 89.4 % 
* K-nearest Neighbour - 83.2 % 
* Random Forest Classifier (RFC) - 94.9 % 
* Bagging Classifier - 89.9 % 
* Gradient Boosting - 89.8 % 

The most accurate one being the RFC model, with an accuracy of 94.9 % as mentioned above. More specifically, of the 59 sets of data used for testing, the RFC model accurately predicted 56. The model can be found in the repository labelled: 'model.sav'. 

## Frontend

Along with the model, I implemented a user interface (UI) to perform a diagnosis. It records the "patient's" sustained pronounciation of the vowel a, (which according to: 'Exploiting Nonlinear Recurrence and Fractal Scaling Properties for Voice Disorder Detection', Little MA, McSharry PE, Roberts SJ, Costello DAE, Moroz IM. BioMedical Engineering OnLine 2007, 6:23 (26 June 2007) is the most effective in obtaining vocal features). The UI is written in html and I modified an existing version of a wav file recorder, which belongs to [octavn](https://github.com/octavn) and can be found here: https://github.com/addpipe/simple-recorderjs-demo. The frontend code can be found to in the folder labelled: "frontend". 
Note: The wav file recorder **ONLY** works on **Chrome** and **Firefox** browsers.

## Backend

The frontent provides a basic user experience and it is accompanied with backend code, found in the "backend" folder. In the backend, I used flask to accept the wav file which is sent by the frontend, and I route it to a function which extracts vocal features from the recording. The vocal feature extraction is carried out through the 


Notes For Self (Delete):
- Credit the person who made the recorder -- Github
- Only works on Google Chrome and Firefox (Webpage stuff)
- Add link to audio repository
- Add link to Parselmouth Library (+ Credit)
- List python libraries necessary
- Discuss Firebase

Structure for Document (Delete):
- Introduce PD
- Discuss approach (random forest) -- vocal features
- Discuss results -- high accuracy but ineffective in real life (misdiagnosis)
- Discuss resources (i.e audio repository + library)
- Discuss Frontend (Credit Github Link)
- Discuss Backend
- Conclusion -- Improvements are welcome


