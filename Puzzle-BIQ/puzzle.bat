cmd /c "mvn clean package -D skipTests=true"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test0_nR_t1.in -output c:\temp\test0_nR_t1.out -thread 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test2_nR_t2.in -output c:\temp\test2_nR_t2.out -thread 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test4_nR_t3.in -output c:\temp\test4_nR_t3.out -thread 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test6_nR_t4.in -output c:\temp\test6_nR_t4.out -thread 2"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test1_R_t1.in -output c:\temp\test1_R_t1.out -thread 2 -rotate"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test3_R_t2.in -output c:\temp\test3_R_t2.out -thread 2 -rotate"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test5_R_t3.in -output c:\temp\test5_R_t3.out -thread 2 -rotate"
cmd /c "java -jar target\puzzle-0.0.1-SNAPSHOT.jar -input c:\puzzle-biq\test7_R_t4.in -output c:\temp\test7_R_t4.out -thread 2 -rotate"