
1. Manual / Help
bashbash gestion.sh --manual
When it asks for input, try the easter eggs:
R1
R1
circulo
R2
arriba
abajo
arriba
abajo
arriba
abajo
done
Or the Fibonacci one:
Fibo
Fibo
8
done

2. createDirs — directory tree
bashbash gestion.sh -d proyecto + src + components - utils - docs
Then verify it worked:
bashfind proyecto -type d
Expected result:
proyecto/src/components
proyecto/src/utils    ← wait, trace it yourself!
proyecto/docs
Try tracing it step by step with the stack table I showed you earlier.

3. listProcesses
bashbash gestion.sh -p root
Or your own username:
bashbash gestion.sh -p $(whoami)
Then check the log file it created:
bashcat procesos_*.log

4. listFiles
bashbash gestion.sh -l /etc
Or your home:
bashbash gestion.sh -l
Check the saved log:
bashcat /etc/ficheros_*.log

5. killProcess
First, create a fake background process to kill:
bashsleep 999 &
That prints a PID like [1] 4821. Then kill it:
bashbash gestion.sh -k 4821
Type y to confirm. Verify it's gone:
bashps aux | grep sleep

6. backupHome
bashbash gestion.sh --backup
Check it worked:
bashls ~/.backups/

7. querySql
You need MySQL running with a database. If you have one:
bashbash gestion.sh -s futbol jugadores
bash gestion.sh -s futbol jugadores id_jugador nombre
If you don't have MySQL, just show the professor what the command would produce — the logic is what matters.

8. rotateLog
First make sure the log has something in it:
bashbash gestion.sh -l /etc
cat gestion.log
Then rotate it:
bashbash gestion.sh -log
ls gestion*.log
You should see the old one archived and a new empty gestion.log.

9. manageGroup
bash# List your current groups
bash gestion.sh -g

# Create a group
sudo bash gestion.sh -g testgroup

# Create with specific GID
sudo bash gestion.sh -g testgroup2 1500

# Delete a group
sudo bash gestion.sh -g -r testgroup

10. manageUser
bash# Show current user's full name
bash gestion.sh -u

# Create a user (needs sudo)
sudo bash gestion.sh -u testuser testgroup

# Delete the user
sudo bash gestion.sh -u -r testuser

11. listOrdered
bashbash gestion.sh -lo /etc
Or create a test folder with files of different name lengths:
bashmkdir testdir
touch testdir/a.txt testdir/go.sh testdir/hello.py testdir/readme.md
bash gestion.sh -lo testdir
Expected staircase:
 a.txt
  go.sh
      hello.py
       readme.md

12. networkMenu
bashbash gestion.sh -n
Choose 1 and ping:
google.com
Or try a fake host to see the "not reachable" message:
999.999.999.999

Check the log after everything
bashcat gestion.log
You should see every command you ran recorded with timestamps.