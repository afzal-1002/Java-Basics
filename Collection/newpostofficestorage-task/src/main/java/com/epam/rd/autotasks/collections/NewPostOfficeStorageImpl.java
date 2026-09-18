package com.epam.rd.autotasks.collections;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class NewPostOfficeStorageImpl implements NewPostOfficeStorage {
    // this list keeps all the boxes that are currently in the storage
    private List<Box> parcels;

    /**
     * Creates internal storage for becoming parcels
     */
    public NewPostOfficeStorageImpl() {
        parcels = new LinkedList<>();
    }

    /**
     * Creates own storage and appends all parcels into own storage.
     * It must add either all the parcels or nothing, if an exception occurs.
     *
     * @param boxes a collection of parcels.
     * @throws NullPointerException if the parameter is {@code null}
     *                              or contains {@code null} values.
     */
    public NewPostOfficeStorageImpl(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("boxes is null");
        }

        // first put everything in a temporary list
        // if we find a null box we stop and nothing is saved
        List<Box> temporaryList = new LinkedList<>();
        for (Box box : boxes) {
            if (box == null) {
                throw new NullPointerException("boxes contains a null value");
            }
            temporaryList.add(box);
        }

        parcels = temporaryList;
    }

    @Override
    public boolean acceptBox(Box box) {
        if (box == null) {
            throw new NullPointerException("box is null");
        }
        parcels.add(box);
        return true;
    }

    @Override
    public boolean acceptAllBoxes(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("boxes is null");
        }

        // check everything first, so if something is wrong
        // we don't add half of the boxes
        List<Box> temporaryList = new ArrayList<>();
        for (Box box : boxes) {
            if (box == null) {
                throw new NullPointerException("boxes contains a null value");
            }
            temporaryList.add(box);
        }

        if (temporaryList.isEmpty()) {
            return false;
        }

        parcels.addAll(temporaryList);
        return true;
    }

    @Override
    public boolean carryOutBoxes(Collection<Box> boxes) {
        if (boxes == null) {
            throw new NullPointerException("boxes is null");
        }

        // copy the boxes we need to remove and check for nulls first
        List<Box> boxesToRemove = new ArrayList<>();
        for (Box box : boxes) {
            if (box == null) {
                throw new NullPointerException("boxes contains a null value");
            }
            boxesToRemove.add(box);
        }

        boolean wasChanged = false;
        Iterator<Box> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            Box currentBox = iterator.next();

            // manually check if currentBox is one of the boxes we want to remove
            boolean shouldBeRemoved = false;
            for (Box boxToRemove : boxesToRemove) {
                if (currentBox.equals(boxToRemove)) {
                    shouldBeRemoved = true;
                    break;
                }
            }

            if (shouldBeRemoved) {
                iterator.remove();
                wasChanged = true;
            }
        }

        return wasChanged;
    }

    @Override
    public List<Box> carryOutBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("predicate is null");
        }

        List<Box> removedBoxes = new ArrayList<>();
        Iterator<Box> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            Box currentBox = iterator.next();
            if (predicate.test(currentBox)) {
                removedBoxes.add(currentBox);
                iterator.remove();
            }
        }

        return removedBoxes;
    }

    @Override
    public List<Box> getAllWeightLessThan(final double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be greater than zero");
        }

        Predicate<Box> weightPredicate = new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getWeight() < weight;
            }
        };

        return searchBoxes(weightPredicate);
    }

    @Override
    public List<Box> getAllCostGreaterThan(final BigDecimal cost) {
        if (cost == null) {
            throw new NullPointerException("cost is null");
        }
        if (cost.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("cost must not be less than zero");
        }

        Predicate<Box> costPredicate = new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getCost().compareTo(cost) > 0;
            }
        };

        return searchBoxes(costPredicate);
    }

    @Override
    public List<Box> getAllVolumeGreaterOrEqual(final double volume) {
        if (volume < 0) {
            throw new IllegalArgumentException("volume must not be less than zero");
        }

        Predicate<Box> volumePredicate = new Predicate<Box>() {
            @Override
            public boolean test(Box box) {
                return box.getVolume() >= volume;
            }
        };

        return searchBoxes(volumePredicate);
    }

    @Override
    public List<Box> searchBoxes(Predicate<Box> predicate) {
        if (predicate == null) {
            throw new NullPointerException("predicate is null");
        }

        List<Box> foundBoxes = new ArrayList<>();
        for (Box box : parcels) {
            if (predicate.test(box)) {
                foundBoxes.add(box);
            }
        }

        return foundBoxes;
    }

    @Override
    public void updateOfficeNumber(Predicate<Box> predicate, int newOfficeNumber) {
        if (predicate == null) {
            throw new NullPointerException("predicate is null");
        }
        if (newOfficeNumber <= 0) {
            throw new IllegalArgumentException("newOfficeNumber must be greater than zero");
        }

        for (Box box : parcels) {
            if (predicate.test(box)) {
                box.setOfficeNumber(newOfficeNumber);
            }
        }
    }
}
