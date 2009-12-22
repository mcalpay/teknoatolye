package org.mca.ewall.jsf;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

/**
 * @author malpay
 */
public class ProxyPhaseListener
        implements PhaseListener {

    private Map<Integer, Set<Target>> afterregistry;
    private Map<Integer, Set<Target>> beforeregistry;
    
    public ProxyPhaseListener() {
        try {
            URL ucp = new URL(ClasspathUrlFinder.findClassBase(getClass()).toString().replaceAll("%20", " "));
            AnnotationDB db = new AnnotationDB();
            db.setScanClassAnnotations(false);
            db.setScanFieldAnnotations(false);
            db.setScanParameterAnnotations(false);
            db.scanArchives(ucp);
            afterregistry = new HashMap<Integer, Set<Target>>();
            beforeregistry = new HashMap<Integer, Set<Target>>();
            Map<String, Set<String>> map = db.getAnnotationIndex();
            registerMethods(map, afterregistry, new AfterPhaseRegisterer());
            registerMethods(map, beforeregistry, new BeforePhaseRegisterer());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void afterPhase(PhaseEvent event) {
        invoke(event, afterregistry);
    }

    private void invoke(PhaseEvent event,Map<Integer, Set<Target>> registry) {
        Set<Target> targets = registry.get(event.getPhaseId().getOrdinal());
        if (targets != null) {
            for (Target target : targets) {
                target.invoke();
            }
        }
    }

    public void beforePhase(PhaseEvent event) {
        invoke(event, beforeregistry);
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private void registerMethods(Map<String, Set<String>> annotationIndex,
            Map<Integer, Set<Target>> registry,
            PhaseRegisterer phaseRegisterer) throws ClassNotFoundException, SecurityException {
        Set<String> classes = annotationIndex.get(phaseRegisterer.getPhaseMetaData().getName());
        if(classes != null) {
            for (String c : classes) {
                Class clazz = Class.forName(c);
                Method[] ms = clazz.getDeclaredMethods();
                for (Method m : ms) {
                    for (Annotation an : m.getDeclaredAnnotations()) {
                        Class antype = an.annotationType();
                        if (antype.equals(phaseRegisterer.getPhaseMetaData())) {
                            phaseRegisterer.register(an, registry, m, clazz);
                        }
                    }
                }
            }
        }
        
    }

    protected class AfterPhaseRegisterer implements PhaseRegisterer {
        public void register(Annotation an, Map<Integer, Set<Target>> registry, Method m, Class clazz) {
            AfterPhase ap = (AfterPhase) an;
            Integer key = ap.value().ordinal();
            Set<Target> value = registry.get(key);
            if (value == null) {
                value = new HashSet<Target>();
            }
            value.add(new Target(m, clazz));
            registry.put(key, value);
        }

        public Class<? extends Annotation> getPhaseMetaData() {
            return AfterPhase.class;
        }
    }

    protected class BeforePhaseRegisterer implements PhaseRegisterer {

        public void register(Annotation an, Map<Integer, Set<Target>> registry, Method m, Class clazz) {
            BeforePhase ap = (BeforePhase) an;
            Integer key = ap.value().ordinal();
            Set<Target> value = registry.get(key);
            if (value == null) {
                value = new HashSet<Target>();
            }
            value.add(new Target(m, clazz));
            registry.put(key, value);
        }

        public Class<? extends Annotation> getPhaseMetaData() {
            return BeforePhase.class;
        }
    }

    interface PhaseRegisterer {
        void register(Annotation an, Map<Integer, Set<Target>> registry, Method m, Class clazz);
        public Class<? extends Annotation> getPhaseMetaData();
    }

    protected class Target {
        private Method method;
        private Class clazz;

        public Target(Method method, Class clazz) {
            this.method = method;
            this.clazz = clazz;
        }

        private void invoke() {
            try {
                method.invoke(null);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Target other = (Target) obj;
            if (this.method != other.method && (this.method == null || !this.method.equals(other.method))) {
                return false;
            }
            if (this.clazz != other.clazz && (this.clazz == null || !this.clazz.equals(other.clazz))) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 47 * hash + (this.method != null ? this.method.hashCode() : 0);
            hash = 47 * hash + (this.clazz != null ? this.clazz.hashCode() : 0);
            return hash;
        }

    }
    
}
