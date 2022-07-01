~~~~~~__~~**``**~~__~~~~~~Graphflow Optimizers
--------------------

<img src="docs/img/graphflow.png" height="181px" weight="377">

Table of Contents
-----------------
  * [Overview](#overview)
  * [Build steps](#Build Steps)
  * [Executing Queries](#Executing Queries)
  * [Contact](#contact)

Overview
-----------------
For an overview on Graphflow, check our [Query Optimizer Paper](https://arxiv.org/abs/1903.02076).      
Copyright © 2016-2019. DO NOT DISTRIBUTE. USE ONLY FOR ACADEMIC RESEARCH PURPOSES.   

Build Steps
-----------------
* To do a full clean build: `./gradlew clean build installDist`
* All subsequent builds: `./gradlew build installDist`

Executing Queries
-----------------
### Getting Started
After building Graphflow, run the following command in the project root directory:
```
. ./env.sh
```
You can now move into the scripts folder to load a dataset and dominance queries:
```
cd scripts
```
	
### Dataset Preperation
A dataset may consist of a single edges file i.e. all vertices have the same label or two separate files, one for vertices and one for edges. We have provided two example datasets in the `datasets` folder. The `serialize_dataset.py` script lets you load datasets from csv files and serialize them to Graphflow format for quick subsequent loading. 

To load and serialize a dataset from a single edges files, run the following command in the `scripts` folder:
```
python3 serialize_dataset.py /absolute/path/edges.csv /absolute/path/data 
```
The system will assume that all vertices have the same type in this case. The serialized graph will be stored in the `data` directory. If the dataset consists of an edges file and a vertices file, the following command can be used instead:
```
python3 serialize_dataset.py /absolute/path/edges.csv /absolute/path/data -v /absolute/path/vertices.csv
```
After running one of the commands above, a catalog can be generated for the optimizer using the `serialize_catalog.py` script. 
```
python3 serialize_catalog.py /absolute/path/data  
```

### Executing Query
Once a dataset has been prepared, executing a query is as follows:
```
python3 execute_query.py "(a)->(b), (b)->(c)" /absolute/path/data
```
An output example is where optimization runtime is 7ms, query runtime is 859.9ms, and the number of output tuples is 11,595,668:
```
Optimizer runtime: 7.931627 (ms)   
Run-time(ms),numOutputTuples,numIntermediateTuples,IntersectionCost operators<IntersectionCost|numOutputTuples>      
QueryPlan output:859.8555,11595668,3387388,154189672,SCAN (a)->(c),3387388,Multi-pre_dataset.Edge-Extend TO (b) From (a[Fwd]-c[Bwd]),154189672,11595668   
```

The optimization runtime as well as the query plan and the quey execution runtime are logged.

The query above assigns an arbitrary edge and vertex labels to (a), (b), (c), (a)->(b), and (b)->(c). Use it with unlabeled datasets only.

When the dataset has labels, assign labels to each vertex and edge as follows:
```
python3 execute_query.py "(a:person)-[friendof]->(b:person), (b:person)-[likes]->(c:movie)" /absolute/path/data
```

Contact
-----------------
[Amine Mhedhbi](http://amine.io/), amine.mhedhbi@uwaterloo.ca

