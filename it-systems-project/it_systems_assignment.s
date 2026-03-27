                     LDR     R0, =0 ; P1 Arrival Time
                     LDR     R1, =2 ; P2 Arrival Time
                     LDR     R2, =4 ; P3 Arrival Time
                     LDR     R3, =10 ; P1 Burst Time
                     LDR     R4, =8 ; P2 Burst Time
                     LDR     R5, =6 ; P3 Burst Time
                     LDR     R6, =2 ; Time Quantum
                     ;       Initialize variables for calculations
                     MOV     R7, #0 ; Clock Time
                     MOV     R8, #0 ; Sum of Turnaround Time
                     MOV     R9, #0 ; P1 Completion Time
                     MOV     R10, #0 ; P2 Completion Time
                     MOV     R11, #0 ; P3 Completion Time

                     ;       Ensure the clock starts at the arrival time of the first process
                     CMP     R7, R0
                     BGE     start_loop
                     MOV     R7, R0

start_loop           
loop                 
                     ;       Check if P1 is finished
                     CMP     R3, #0
                     BLE     skip_p1
                     CMP     R7, R0
                     BLT     skip_p1
                     SUBS    R3, R3, R6
                     ADD     R7, R7, R6
                     BHI     skip_p1
                     MOV     R9, R7 ; Save completion time of P1
skip_p1              
                     ;       Check if P2 is finished
                     CMP     R4, #0
                     BLE     skip_p2
                     CMP     R7, R1
                     BLT     skip_p2
                     SUBS    R4, R4, R6
                     ADD     R7, R7, R6
                     BHI     skip_p2
                     MOV     R10, R7 ; Save completion time of P2
skip_p2              
                     ;       Check if P3 is finished
                     CMP     R5, #0
                     BLE     check_finished
                     CMP     R7, R2
                     BLT     check_finished
                     SUBS    R5, R5, R6
                     ADD     R7, R7, R6
                     BHI     loop
                     MOV     R11, R7 ; Save completion time of P3
check_finished       
                     ;       Check if all processes are finished
                     CMP     R3, #0
                     BGT     loop
                     CMP     R4, #0
                     BGT     loop
                     CMP     R5, #0
                     BGT     loop

calculate_turnaround 
                     ;       Calculate and store turnaround times for all processes
                     SUB     R9, R9, R0 ; Turnaround for P1
                     SUB     R10, R10, R1 ; Turnaround for P2
                     SUB     R11, R11, R2 ; Turnaround for P3

                     ADD     R8, R9, R10
                     ADD     R8, R8, R11 ; Sum of all turnaround times

                     ;       Calculate average turnaround time using division
                     MOV     R0, R8
                     MOV     R1, #3
                     MOV     R2, #0

div_loop             
                     CMP     R0, R1
                     BLT     done
                     SUB     R0, R0, R1
                     ADD     R2, R2, #1
                     B       div_loop
done                 


                     END