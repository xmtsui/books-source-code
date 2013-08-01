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

import java.util.*;

class RWNode {
        static final int READER = 0;
        static final int WRITER = 1;
        Thread t;
        int state;
        int nAcquires;
        RWNode(Thread t, int state) {
                this.t = t;
                this.state = state;
                nAcquires = 0;
        }
}

public class RWLock {
        private Vector waiters;

        private int firstWriter() {
                Enumeration e;
                int index;
                for (index = 0, e = waiters.elements();
                                                                   e.hasMoreElements(); index++) {
                        RWNode node = (RWNode) e.nextElement();
                        if (node.state == RWNode.WRITER)
                                return index;
                }
                return Integer.MAX_VALUE;
        }

        private int getIndex(Thread t) {
                Enumeration e;
                int index;
                for (index = 0, e = waiters.elements();
                                                 e.hasMoreElements(); index++) {
                        RWNode node = (RWNode) e.nextElement();
                        if (node.t == t)
                                return index;
                }
                return -1;
        }

        public RWLock() {
                waiters = new Vector();
        }

        public synchronized void lockRead() {
                RWNode node;
                Thread me = Thread.currentThread();
                int index = getIndex(me);
                if (index == -1) {
                        node = new RWNode(me, RWNode.READER);
                        waiters.addElement(node);
                }
                else node = (RWNode) waiters.elementAt(index);
                while (getIndex(me) > firstWriter()) {
                        try {
                                wait();
                        } catch (Exception e) {}
                }
                node.nAcquires++;
        }
        public synchronized void lockWrite() {
                RWNode node;
                Thread me = Thread.currentThread();
                int index = getIndex(me);
                if (index == -1) {
                        node = new RWNode(me, RWNode.WRITER);
                        waiters.addElement(node);
                }
                else {
                        node = (RWNode) waiters.elementAt(index);
                        if (node.state == RWNode.READER)
                                throw new IllegalArgumentException("Upgrade lock");
                        node.state = RWNode.WRITER;
                }
                while (getIndex(me) != 0) {
                        try {
                                wait();
                        } catch (Exception e) {}
                }
                node.nAcquires++;
        }

        public synchronized void unlock() {
                RWNode node;
                Thread me = Thread.currentThread();
                int index;
                index = getIndex(me);
                if (index  > firstWriter())
                        throw new IllegalArgumentException("Lock not held");
                node = (RWNode) waiters.elementAt(index);
                node.nAcquires--;
                if (node.nAcquires == 0) {
                        waiters.removeElementAt(index);
                        notifyAll();
                }
        }
}
