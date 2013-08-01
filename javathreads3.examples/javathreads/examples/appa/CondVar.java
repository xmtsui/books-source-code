/*
 *
 * Copyright (c) 1997-1999 Scott Oaks and Henry Wong. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and
 * without fee is hereby granted.
 *
 * This sample source code is provided for example only,
 * on an unsupported, as-is basis. 
 *
 * AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. AUTHOR SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
 * CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
 * PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
 * NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
 * SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
 * SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
 * PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES").  AUTHOR
 * SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
 * HIGH RISK ACTIVITIES.
 */

package javathreads.examples.appa;

public class CondVar {
        private BusyFlag SyncVar;

        public CondVar() {
                this(new BusyFlag());
        }

        public CondVar(BusyFlag sv) {
                SyncVar = sv;
        }

        public void cvWait() throws InterruptedException {
                cvTimedWait(SyncVar, 0);
        }

        public void cvWait(BusyFlag sv) throws InterruptedException {
                cvTimedWait(sv, 0);
        }

        public void cvTimedWait(int millis) throws InterruptedException {
                cvTimedWait(SyncVar, millis);
        }

        public void cvTimedWait(BusyFlag sv, int millis) throws InterruptedException {
                int i = 0;
                InterruptedException errex = null;

                synchronized (this) {
                        // You must own the lock in order to use this method
                        if (sv.getBusyFlagOwner() != Thread.currentThread()) {
                                throw new IllegalMonitorStateException("current thread not owner");
                        }

                        // Release the lock (Completely)
                        while (sv.getBusyFlagOwner() == Thread.currentThread()) {
                                i++;
                                sv.freeBusyFlag();
                        }
                
                        // Use wait() method        
                        try {
                                if (millis == 0) {
                                        wait();
                                } else {
                                        wait(millis);
                                }
                        } catch (InterruptedException iex) {
                                errex = iex;
                        }
                }
         
                // Obtain the lock (Return to original state)
                for (; i>0; i--) {
                        sv.getBusyFlag();
                }

                if (errex != null) throw errex;
                return;
        }

        public void cvSignal() {
                cvSignal(SyncVar);
        }

        public synchronized void cvSignal(BusyFlag sv) {
                // You must own the lock in order to use this method
                if (sv.getBusyFlagOwner() != Thread.currentThread()) {
                        throw new IllegalMonitorStateException("current thread not owner");
                }
                notify();
        }

        public void cvBroadcast() {
                cvBroadcast(SyncVar);
        }

        public synchronized void cvBroadcast(BusyFlag sv) {
                // You must own the lock in order to use this method
                if (sv.getBusyFlagOwner() != Thread.currentThread()) {
                        throw new IllegalMonitorStateException("current thread not owner");
                }
                notifyAll();
        }
}
