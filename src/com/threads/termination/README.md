## Thread Termination, Why and When?
* Thread consume resources
    - Memory and Kernel resources
    - CPU cycles and Cache Memory
    
* If the thread finished its work, but the app is still running, we want to clean up the thread's resources
* If the thread is misbehaving(loop, huge computation/calculation etc), we want ot stop it.
* By default, app won't stop if atleast one thread is running.

### Thread.interrupt()
**When?**
* If the thread is executing a method that throws interrupted exception.
* If the thread's code is handling the interrupt signal explicitly
* If the method is not responding to the interrupt signal, we have to check the interrupt signal in the method using 
  isInterrupted() on the current thread and hande the scenario

### Daemon Threads
* Run in background, Do not prevent the application from exiting if the main thread terminates
* Use for background tasks e.g File saving in text editor. Keeps saving our file periodically on background
* Code in a worker thread which is not in our control, and we do not want it to block our application from terminating