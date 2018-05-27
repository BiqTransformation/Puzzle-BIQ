cmd /c "mvn clean package -D skipTests=true"
echo "====================== Amir's tests ======================
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test0_nR_t1.in -output c:\temp\test0_nR_t1.out -threads 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar  -output c:\temp\test2_nR_t2.out -input c:\puzzle-biq\test2_nR_t2.in -threads 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar  -threads 2 -input c:\puzzle-biq\test4_nR_t3.in -output c:\temp\test4_nR_t3.out"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test6_nR_t4.in -output c:\temp\test6_nR_t4.out"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar  -rotate -input c:\puzzle-biq\test1_R_t1.in -output c:\temp\test1_R_t1.out -threads 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -output c:\temp\test3_R_t2.out -input c:\puzzle-biq\test3_R_t2.in -threads 2 -rotate"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test5_R_t3.in -output c:\temp\test5_R_t3.out -rotate -threads 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test7_R_t4.in -output c:\temp\test7_R_t4.out -rotate"

echo "================== Usage tests =====================
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input -output c:\temp\test7_R_t4.out -threads 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar c:\puzzle-biq\test7_R_t4.in -output c:\temp\test7_R_t4.out -threads 2 -rotate"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test7_R_t4.in c:\temp\test7_R_t4.out -threads 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test7_R_t4.in -output -threads 2 -rotate"

