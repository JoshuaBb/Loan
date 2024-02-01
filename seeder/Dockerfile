FROM python
WORKDIR /app
COPY requirements.txt /app
RUN ["pip", "install", "-r", "requirements.txt"]
COPY loan-seeder.py /app
COPY LoanStats_securev1_2017Q4.csv /app
CMD ["./loan-seeder.py"]