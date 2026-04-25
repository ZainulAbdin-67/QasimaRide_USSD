# QasimaRide USSD - System Flowchart

## 1. Main Menu Flowchart

```
┌─────────────────────────────────────────┐
│   QASIMARIDE USSD SYSTEM START (*789#) │
│   Initialize StandManager               │
│   Load Sample Data                      │
└────────────────┬────────────────────────┘
                 │
                 ▼
        ┌────────────────────┐
        │   Display Main     │
        │     USSD Menu      │
        └────────┬───────────┘
                 │
        ┌────────┴─────────┬──────────────┬────────────┐
        │                  │              │            │
        ▼                  ▼              ▼            ▼
    ┌────────┐      ┌──────────┐  ┌────────────┐  ┌──────┐
    │ Driver │      │Passenger │  │ Admin      │  │ Exit │
    │ Menu   │      │ Menu     │  │ Status     │  │      │
    └────┬───┘      └─────┬────┘  └─────┬──────┘  └──┬───┘
         │                │             │            │
         │                │             │            ▼
         │                │             │        ┌────────────┐
         │                │             │        │ Exit System│
         │                │             │        │ (GOODBYE)  │
         │                │             │        └────────────┘
         │                │             │
         └────────────────┴─────────────┘
                │
         [Return to Main Menu]
```

---

## 2. Driver Menu Flowchart

```
┌─────────────────────────────┐
│   DRIVER MENU               │
│   Enter Driver ID           │
└────────────┬────────────────┘
             │
        ┌────┴─────┐
        │           │
        ▼           ▼
    ┌────────┐  ┌──────────┐
    │Existing│  │  Create  │
    │Driver  │  │  New     │
    │Found?  │  │  Driver  │
    └────┬───┘  └────┬─────┘
         │           │
         └─────┬─────┘
               │
               ▼
    ┌──────────────────────┐
    │ Display Driver Menu: │
    │ 1. View Profile      │
    │ 2. Toggle Avail.     │
    │ 3. View Stand        │
    │ 4. Complete Ride     │
    │ 5. Back to Main      │
    └──────────┬───────────┘
               │
        ┌──────┼──────┬──────────┬─────────┐
        │      │      │          │         │
        ▼      ▼      ▼          ▼         ▼
    ┌─────┐┌──────┐┌────┐┌──────┐┌─────────┐
    │View ││Toggle│View │Complete│Return to│
    │Prof.││Avail││Stand│ Ride   │Main Menu│
    └──┬──┘└───┬──┘└──┬─┘└───┬───┘└─────────┘
       │       │      │      │
       ▼       ▼      ▼      ▼
    [Show] [Update] [Show]  [Mark
    Profile Status  Status Available]
       │       │      │      │
       └───────┴──────┴──────┘
             │
       [Return to Driver Menu]
```

---

## 3. Passenger Menu Flowchart

```
┌──────────────────────────────┐
│   PASSENGER MENU             │
│   Enter Passenger ID         │
└────────────┬─────────────────┘
             │
        ┌────┴──────┐
        │            │
        ▼            ▼
    ┌────────┐  ┌──────────┐
    │Existing│  │  Create  │
    │Pass.   │  │  New     │
    │Found?  │  │  Passenger│
    └────┬───┘  └────┬─────┘
         │           │
         └─────┬─────┘
               │
               ▼
    ┌──────────────────────┐
    │ Display Pass. Menu:  │
    │ 1. View Profile      │
    │ 2. Request Ride      │
    │ 3. Check Queue       │
    │ 4. Back to Main      │
    └──────────┬───────────┘
               │
        ┌──────┼──────┬─────────┐
        │      │      │         │
        ▼      ▼      ▼         ▼
    ┌─────┐┌──────┐┌─────┐┌──────────┐
    │View │Request│Check │Return to  │
    │Prof.│ Ride  │Queue │Main Menu  │
    └──┬──┘└───┬──┘└──┬──┘└──────────┘
       │       │      │
       ▼       ▼      ▼
    [Show] [Select [Show
    Profile Stand] Queue
       │    │      Status]
       │    ▼
       │  [Enter
       │   Source &
       │   Destination]
       │    │
       │    ▼
       │  [Add to
       │   Queue
       │   (FIFO)]
       │
       └─────┬──────┘
             │
       [Return to Passenger Menu]
```

---

## 4. Ride Request & Allocation Flowchart

```
┌────────────────────────────┐
│ PASSENGER REQUESTS RIDE    │
└────────────┬───────────────┘
             │
             ▼
    ┌─────────────────────┐
    │ Select Stand:       │
    │ CHARMINAR           │
    │ SECUNDERABAD        │
    │ GACHIBOWLI          │
    └────────┬────────────┘
             │
             ▼
    ┌─────────────────────┐
    │ Enter:              │
    │ - Source Location   │
    │ - Destination       │
    └────────┬────────────┘
             │
             ▼
    ┌─────────────────────┐
    │ Add Passenger to    │
    │ Stand Queue (FIFO)  │
    └────────┬────────────┘
             │
             ▼
    ┌─────────────────────┐
    │ Confirm Request     │
    │ ✓ Added to Queue    │
    └────────┬────────────┘
             │
             ▼
    ┌──────────────────────────────┐
    │ ADMIN ALLOCATES RIDE         │
    │ (Via Admin Panel)            │
    └────────────┬─────────────────┘
                 │
                 ▼
    ┌─────────────────────────────┐
    │ Check Stand Queue           │
    │ Any Passengers Waiting?     │
    └────────┬────────────────────┘
             │
        ┌────┴────────┐
        │             │
        ▼             ▼
    ┌────────┐    ┌───────────┐
    │  YES   │    │   NO      │
    │        │    │           │
    └────┬───┘    └─────┬─────┘
         │              │
         ▼              ▼
    ┌──────────────┐ ┌──────────────┐
    │Get Available │ │ No Passengers│
    │ Drivers      │ │ Error Message│
    └────┬─────────┘ └──────────────┘
         │
         ▼
    ┌──────────────────┐
    │ Any Available?   │
    └────┬─────────────┘
         │
    ┌────┴────────┐
    │             │
    ▼             ▼
┌────────┐    ┌───────────┐
│  YES   │    │   NO      │
│        │    │           │
└────┬───┘    └─────┬─────┘
     │              │
     ▼              ▼
┌──────────────┐ ┌──────────────┐
│Allocate 1st  │ │ No Drivers   │
│Available     │ │ Error Message│
│Driver (FIFO) │ └──────────────┘
└─────┬────────┘
      │
      ▼
 ┌────────────────┐
 │ Update Driver: │
 │ - Status→BUSY  │
 │ - Assign Ride  │
 └─────┬──────────┘
       │
       ▼
 ┌────────────────┐
 │ Remove Pass.   │
 │ from Queue     │
 └─────┬──────────┘
       │
       ▼
 ┌────────────────────────┐
 │ ✓ RIDE ALLOCATED!      │
 │ Passenger: [Name]      │
 │ Driver: [Name]         │
 │ Vehicle: [Number]      │
 │ Route: [Source→Dest]   │
 └────────┬───────────────┘
          │
          ▼
 ┌──────────────────┐
 │ Driver Completes │
 │ Ride (Option 4)  │
 └────────┬─────────┘
          │
          ▼
 ┌──────────────────┐
 │ Driver Status→   │
 │ AVAILABLE        │
 └──────────────────┘
```

---

## 5. Admin Panel Flowchart

```
┌──────────────────────┐
│   ADMIN STATUS       │
│   Enter Password     │
└────────┬─────────────┘
         │
    ┌────┴─────┐
    │           │
    ▼           ▼
┌─────────┐ ┌──────────┐
│Correct  │ │ Incorrect│
│Pass.?   │ │ Pass.    │
└────┬────┘ └────┬─────┘
     │           │
     ▼           ▼
┌─────────────┐┌──────────────┐
│ Display     ││ ✗ Error Msg  │
│ Admin Menu  ││ Return to    │
└────────┬────┘└──────────────┘
         │              Main Menu
    ┌────┼────┬──────┬──────────┐
    │    │    │      │          │
    ▼    ▼    ▼      ▼          ▼
 ┌──┐┌──┐┌──┐┌────┐┌─────────┐
 │1 ││2 ││3 ││ 4  ││    5    │
 │  ││  ││  ││    ││         │
 └─┬┘└─┬┘└─┬┘└──┬─┘└────┬────┘
   │   │   │    │       │
   ▼   ▼   ▼    ▼       ▼
┌────┐┌───┐┌──┐┌──┐┌────────┐
│View││Alloc.│View│View│Return│
│Sys ││Ride ││All ││All│Main  │
│Stat││     ││Driv││Pas│Menu  │
└──┬─┘└──┬──┘└──┬─┘└──┬┘└────┘
   │     │      │     │
   ▼     ▼      ▼     ▼
[Show][Run Ride][List][List]
Stats Allocation All  All
      Process  Drivers Pass.
   │     │      │     │
   └─────┴──────┴─────┘
         │
   [Return to Admin Menu]
```

---

## 6. Data Structure Flow

```
┌────────────────────────────────────────────┐
│        STANDMANAGER (Controller)           │
│  - HashMap<String, RickshawStand> stands   │
│  - ArrayList<Driver> allDrivers            │
│  - ArrayList<Passenger> allPassengers      │
└────────────┬───────────────────────────────┘
             │
     ┌───────┼───────┐
     │       │       │
     ▼       ▼       ▼
┌─────────────────┐ ┌──────────────┐ ┌─────────────────┐
│RICKSHAWSTAND    │ │    DRIVER    │ │   PASSENGER     │
│(Charminar)      │ │  extends USER │ │ extends USER    │
├─────────────────┤ ├──────────────┤ ├─────────────────┤
│- standName      │ │- userId      │ │- userId         │
│- location       │ │- name        │ │- name           │
│- driversAtStand │ │- phone       │ │- phone          │
│  (ArrayList)    │ │- vehicleNum  │ │- sourceLocation │
│- passengerQueue │ │- license     │ │- destination    │
│  (Queue/FIFO)   │ │- isAvailable │ │- hasActiveRide  │
└─────────────────┘ │- currentStand│ └─────────────────┘
                    └──────────────┘
                          ▲
                          │
                    ┌─────┴──────┐
                    │            │
                  USER (Abstract)
                ├─────────────────┤
                │- userId         │
                │- name           │
                │- phoneNumber    │
                │- userType       │
                │+ displayProfile()│
                └─────────────────┘
```

---

## 7. Queue Management (FIFO) Flowchart

```
┌──────────────────────────┐
│   PASSENGER QUEUE (LIFO) │
│   LinkedList Implementation│
└──────────────┬───────────┘
               │
    ┌──────────┴──────────┐
    │                     │
    ▼                     ▼
┌─────────────┐    ┌──────────────┐
│ New Request │    │ Existing Queue│
│ (Passenger) │    │  [P1][P2][P3]│
└──────┬──────┘    └──────┬───────┘
       │                  │
       ▼                  ▼
   [Add]            [Peek/Poll]
   │                │
   ▼                ▼
[P1][P2][P3][NEW]  [P1] → Allocate
                   │
                   ▼
              [P2][P3][NEW]
              (Removed from Queue)

Legend:
[P1] = First Passenger (First In)
[P2] = Second Passenger
[P3] = Third Passenger
[NEW] = New Request (Last In)

FIFO Logic: First request gets ride first
```

---

## 8. Class Relationships (UML)

```
┌─────────────────────────────────────────────────────────────────┐
│                          SYSTEM ARCHITECTURE                    │
└─────────────────────────────────────────────────────────────────┘

┌──────────────────────┐
│      Main Class      │
│  - main(String[])    │
│  - initializeSampleData()
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│   MenuHandler        │
│  - standManager      │
│  - scanner           │
│  - showMainMenu()    │
│  - driverMenu()      │
│  - passengerMenu()   │
│  - adminMenu()       │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────────────┐
│   StandManager               │
│  - stands (HashMap)          │
│  - allDrivers                │
│  - allPassengers             │
│  + registerDriver()          │
│  + registerPassenger()       │
│  + requestRide()             │
│  + allocateRide()            │
│  + completeRide()            │
└──────────┬────────────────────┘
           │
      ┌────┴────┐
      │          │
      ▼          ▼
┌──────────────────┐  ┌───────────────────────┐
│ RickshawStand    │  │ User (Abstract)       │
│  - standName     │  │  - userId             │
│  - drivers[]     │  │  - name               │
│  - passengerQ    │  │  - phone              │
│  + addDriver()   │  │  + displayProfile()   │
│  + addPassenger()│  └───────────┬───────────┘
│  + getNextPass() │              │
└──────────────────┘         ┌────┴─────┐
                             │          │
                             ▼          ▼
                        ┌──────────┐┌──────────────┐
                        │ Driver   ││  Passenger   │
                        ├──────────┤├──────────────┤
                        │- vehicle ││- source      │
                        │- license ││- destination │
                        │- isAvail ││- hasActiveRide
                        └──────────┘└──────────────┘
```

---

## 9. Complete System Flow Summary

```
    START SYSTEM
         │
         ▼
    LOAD DATA
         │
    ┌────┴─────┐
    │           │
    ▼           ▼
┌────────┐ ┌──────────┐ ┌──────────┐
│ DRIVER │ │PASSENGER │ │ ADMIN    │
│ LOGIN  │ │ LOGIN    │ │ LOGIN    │
└────┬───┘ └─────┬────┘ └────┬─────┘
     │           │           │
     ▼           ▼           ▼
 [Driver    [Request]  [Manage
  Options]   [Ride]     System]
     │           │           │
     ▼           ▼           ▼
 [View/   [Add to]  [Allocate]
  Toggle] [Queue]   [Rides]
     │       │       │
     └───────┴───────┘
         │
   [RETURN TO MAIN]
         │
    [REPEAT or EXIT]
```

---

## 10. Technology Stack Flow

```
┌─────────────────────────────────────────┐
│          TECHNOLOGY STACK               │
└─────────────────────────────────────────┘
                 │
    ┌────────────┼────────────┐
    │            │            │
    ▼            ▼            ▼
┌────────────┐┌───────────┐┌──────────────┐
│   JAVA 17  ││  MAVEN    ││ Collections  │
│ (Language) ││(Build)    ││(ArrayList,   │
│            ││           ││ Queue,HashMap)
└────────────┘└───────────┘└──────────────┘
    │            │            │
    └────────────┼────────────┘
                 │
                 ▼
        ┌─────────────────┐
        │  COMPILED JAR   │
        │  (Executable)   │
        └─────────────────┘
                 │
                 ▼
        ┌─────────────────┐
        │  RUN ON LINUX   │
        │  (mvn exec:java)│
        └─────────────────┘
```

---

## Legend

```
┌────┐  = Process/Action
└────┘

   │   = Flow Direction (Down)
   ──  = Flow Direction (Sideways)

   ▼   = Arrow Down
   ◄   = Arrow Left
   ►   = Arrow Right

[...]  = Decision/Condition
(...) = Data/Input

✓  = Success
✗  = Error/Failure
```

---

## Key Features in Flow

✅ **FIFO Queue** - First passenger gets ride first  
✅ **User Authentication** - Driver/Passenger/Admin  
✅ **Ride Allocation** - Automatic matching  
✅ **Status Tracking** - Driver availability  
✅ **Multi-Stand Management** - 3 stands operational  
✅ **Error Handling** - Invalid inputs managed  

---

**Your QasimaRide USSD system is designed with clear data flow and user interactions!** 🚀
