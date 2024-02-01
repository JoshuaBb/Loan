# How to Run the Seeder

1. Unzip LoanStats_securev1_2017Q4.csv.zip in this directory
2. Remove the header and last couple of lines of the file to make sure csv parse file correctly

```bash
docker build . -t seeder
docker run seeder 
```