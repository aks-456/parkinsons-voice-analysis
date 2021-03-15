# Parkinson's-Voice-Analysis
An attempt to classify Parkinson's Disease based on the extraction of vocal features from patient recordings. 

**Note:** This is just a project for fun.  

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

Along with the model, I implemented a user interface (UI) to perform a diagnosis. It records the user's sustained pronounciation of the vowel a, (which according to: 'Exploiting Nonlinear Recurrence and Fractal Scaling Properties for Voice Disorder Detection', Little MA, McSharry PE, Roberts SJ, Costello DAE, Moroz IM. BioMedical Engineering OnLine 2007, 6:23 (26 June 2007) is the most effective in obtaining vocal features). The UI is written in html and I modified an existing version of a wav file recorder, which belongs to [octavn](https://github.com/octavn) and can be found here: https://github.com/addpipe/simple-recorderjs-demo. The frontend code can be found to in the folder labelled: "frontend". 
Note: The wav file recorder **ONLY** works on **Chrome** and **Firefox** browsers.

## Backend

The frontent provides a basic user experience and it is accompanied with backend code, found in the "backend" folder. In the backend, I redirect the html frontend to a flask API, which routes it to a function to extracts vocal features from the recording. The vocal feature extraction is carried out through the Parselmouth Python library, which can be found here: https://parselmouth.readthedocs.io/en/stable/. The parselmouth library is used to port Praat, a software for voice analytics, into python. With this package, I extract vocal features for each recording and feed them into the ML model (model.sav). This produces an output and displays either a 1, indicating the user has Parkinson's disease, or a 0, indicating the user does not. This is contained in the file: "backend/main.py". 

In addition to the flask server I also use pyrebase to store and retrieve the audio recording of the patient. The frontend sends the audio recording to firebase, and the backend flask server retrieves it each time a user has recorded their voice. Currently I only expect there to be one recording at a time, and therefore did not bother to add functionality to change the name of the recording upon execution of the program. The only change required to make is the Firebase database credentials which are unique for each developer, which must be replaced by the comments indicating to do so on "backend/main.py" and "frontend/index.html". 

## Practical Applications

This program has numerous practical applications as it can be used to diagnose PD efficiently in hospitals as well as homes (assuming an appropriate UI is setup). Unfortunately, despite having a high accuracy, the model turned out to be inneficient upon real testing. I tested out my ML model by asking 20 people within the range of 16-20 years old to test out the UI. While it is practically impossible for someone between the age of 16-20 to positively be diagnosed for PD, the model returned a 1, for 14 out of 20 people. A reason for this apart from the limited number of people who tested it, is that it is possible that the parselmouth library is innacurate in extracting vocal features, or that the dataset used for the model contained limited samples. Currently, I am searching for solutions regarding the innacuracy behind the RFC model and looking to improve its capabilities. 

## Notes

This is **NOT** an accurate solution to PD Diagnosis. I would highly appreciate any suggestions or feedback as I was only recently introduced to ML

