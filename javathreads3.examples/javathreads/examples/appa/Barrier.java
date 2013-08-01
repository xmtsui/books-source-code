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

public class Barrier {
        private int threads2Wait4;
        private InterruptedException iex;

        public Barrier (int nThreads) {
                threads2Wait4 = nThreads;
        }

        public synchronized int waitForRest()
                throws InterruptedException {
                int threadNum = --threads2Wait4;

                if (iex != null) throw iex;
                if (threads2Wait4 <= 0) {
                        notifyAll();
                        return threadNum;
                }
                while (threads2Wait4 > 0) {
                        if (iex != null) throw iex;
                        try {
                                wait();
                        } catch (InterruptedException ex) {
                                iex = ex;
                                notifyAll();
                        }
                }
                return threadNum;
        }

        public synchronized void freeAll() {
                iex = new InterruptedException("Barrier Released by freeAll");
                notifyAll();
        }
}
