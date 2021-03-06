/*
 * Copyright (C) 2010-2012 Klaus Reimer <k@ailis.de>
 * See LICENSE.TXT for licensing information.
 */

package de.ailis.xadrian.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import de.ailis.xadrian.interfaces.GameProvider;
import de.ailis.xadrian.support.Config;
import de.ailis.xadrian.support.I18N;

/**
 * A factory
 * 
 * @author Klaus Reimer (k@ailis.de)
 */
public class Factory implements Serializable, Comparable<Factory>, GameProvider
{
    /** Serial version UID */
    private static final long serialVersionUID = 4851121299100273466L;

    /** The game to which this factory belongs. */
    private final Game game;

    /** The factory id */
    private final String id;

    /** The numeric factory id. */
    private final int nid;

    /** The manufacturer race */
    private final Race race;

    /** The production cycle time */
    private final int cycle;

    /** The product this factory produces in one cycle */
    private final Product product;

    /** The price of this factory */
    private final int price;

    /** The volume of this factory */
    private final int volume;

    /** The resources this factory needs in one cycle to produce its product */
    private final Product[] resources;

    /** The manufacturer sectors */
    private final Sector[] manufacturers;

    /** The ware storage */
    private final Capacity[] capacities;

    /** The message id */
    private final String messageId;

    /** The factory size */
    private final FactorySize size;

    /**
     * Constructor
     * 
     * @param game
     *            The game this factory belongs to. Must not be null.
     * @param nid
     *            The numeric factory id.
     * @param id
     *            The factory id
     * @param size
     *            The factory size
     * @param race
     *            The race
     * @param cycle
     *            The production cycle
     * @param product
     *            The produces product per cycle
     * @param price
     *            The factory price
     * @param volume
     *            The factory volume
     * @param resources
     *            The needed resources per cycle
     * @param storage
     *            The ware storage
     * @param manufacturers
     *            The manufacturer stations
     */
    public Factory(final Game game, final int nid, final String id,
        final FactorySize size, final Race race, final int cycle,
        final Product product, final int price, final int volume,
        final Product[] resources, final Capacity[] storage,
        final Sector[] manufacturers)
    {
        if (game == null)
            throw new IllegalArgumentException("game must be set");
        this.game = game;
        this.nid = nid;
        this.id = id;
        this.size = size;
        this.race = race;
        this.cycle = cycle;
        this.product = product;
        this.price = price;
        this.volume = volume;
        this.resources = resources.clone();
        this.capacities = storage.clone();
        this.manufacturers = manufacturers.clone();
        this.messageId = "factory." + id.substring(0, id.lastIndexOf("-"));
    }

    /**
     * Return the factory id.
     * 
     * @return The factory id
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Returns the numeric factory id.
     * 
     * @return The numeric factory id.
     */
    public int getNid()
    {
        return this.nid;
    }

    /**
     * Return the factory size.
     * 
     * @return The factory size
     */
    public FactorySize getSize()
    {
        return this.size;
    }

    /**
     * Returns true if this factory is a mine.
     * 
     * @return True if this factory is a mine. False if not.
     */
    public boolean isMine()
    {
        return isSiliconMine() || isOreMine() || isIceMine();
    }

    /**
     * Returns true if this factory is a silicon mine.
     * 
     * @return True if this factory is a silicon mine. False if not.
     */
    public boolean isSiliconMine()
    {
        return this.product.getWare().isSiliconWafers();
    }

    /**
     * Returns true if this factory is an ore mine.
     * 
     * @return True if this factory is an ore mine. False if not.
     */
    public boolean isOreMine()
    {
        return this.product.getWare().isOre();
    }

    /**
     * Returns true if this factory is an ice mine.
     * 
     * @return True if this factory is an ice mine. False if not.
     */
    public boolean isIceMine()
    {
        return this.product.getWare().isIce();
    }
    
    /**
     * Checks if this factory is a solar power plant.
     * 
     * @return True if factory is a solar power plant, false if not.
     */
    public boolean isSolarPowerPlant()
    {
        final String wareId = this.product.getWare().getId();
        return wareId.equals("energyCells");
    }

    /**
     * Returns the factory name.
     * 
     * @return The factory name
     */
    public String getName()
    {
        return I18N.getString(this.game, this.messageId);
    }

    /**
     * Returns the race which manufactures this factory.
     * 
     * @return The race
     */
    public Race getRace()
    {
        return this.race;
    }

    /**
     * Returns the sun power which is used as default for the cycle of
     * a solar power plant.
     * 
     * @return The default sun power.
     */
    public Sun getDefaultSun()
    {
        return this.game.getSunFactory().getDefaultSun();
    }

    /**
     * Returns the yield which is used as default for the cycle of mines.
     * 
     * @return The default asteroid yield.
     */
    public int getDefaultYield()
    {
        return 26;
    }

    /**
     * Returns the production cycle.
     * 
     * @return The production cycle
     */
    public int getCycle()
    {
        return this.cycle;
    }

    /**
     * Returns the production cycle as a human readable time string.
     * 
     * @return The production cycle as a string
     */
    public String getDefaultCycleAsString()
    {
        final int cycle = getDefaultCycle();
        if (cycle >= 60 * 60)
            return String.format("%d:%02d:%02d", cycle / 60 / 60, cycle
                % (60 * 60) / 60, cycle % 60);
        else if (cycle >= 60)
            return String.format("%d:%02d", cycle / 60, cycle % 60);
        else
            return Integer.toString(cycle);
    }

    /**
     * Returns the produces product per cycle.
     * 
     * @return The product
     */
    public Product getProduct()
    {
        return this.product;
    }

    /**
     * Returns the factory price.
     * 
     * @return The price
     */
    public int getPrice()
    {
        return this.price;
    }

    /**
     * Returns the factory volume.
     * 
     * @return The volume
     */
    public int getVolume()
    {
        return this.volume;
    }

    /**
     * Returns the needed resources per cycle.
     * 
     * @return The resources
     */
    public Product[] getResources()
    {
        return this.resources.clone();
    }

    /**
     * Returns the storage capacities.
     * 
     * @return The storage capacities
     */
    public Capacity[] getCapacities()
    {
        return this.capacities.clone();
    }

    /**
     * Returns the manufacturer stations.
     * 
     * @return The manufacturer stations
     */
    public Sector[] getManufacturers()
    {
        return this.manufacturers.clone();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        final Factory other = (Factory) obj;
        return new EqualsBuilder().append(this.id, other.id).isEquals();
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Factory o)
    {
        int result = 0;

        // If both factories produce the same product then they are most
        // likely the same factory types with different sizes. So if this
        // is the case then compare the sizes.
        if (!o.product.equals(this.product)
            && o.product.getWare().equals(this.product.getWare()))
        {
            String name1 = getName();
            String name2 = o.getName();
            int pos = name1.lastIndexOf(' ');
            if (pos >= 0) name1 = name1.substring(0, pos);
            pos = name2.lastIndexOf(' ');
            if (pos >= 0) name2 = name2.substring(0, pos);
            if (name1.equals(name2)) result = this.size.compareTo(o.size);
        }

        // If factories were not compares by their size then compare the name
        if (result == 0) result = getName().compareTo(o.getName());

        // If names are the same then compare the race
        if (result == 0) result = getRace().compareTo(o.getRace());

        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getName();
    }

    /**
     * Returns the default cycle. This is only used for displaying and not for
     * any calculation.
     * 
     * @return The default cycle.
     */
    public int getDefaultCycle()
    {
        return getRealCycle(getDefaultSun(), getDefaultYield());
    }

    /**
     * Returns the default product. This is only used for displaying and not
     * for any calculation.
     * 
     * @return The default product.
     */
    public Product getDefaultProduct()
    {
        return getRealProduct(getDefaultSun(), getDefaultYield());
    }

    /**
     * Returns the default resources of this factory. This is only used for
     * displaying and not for any calculation.
     * 
     * @return The resources
     */
    public Product[] getDefaultResources()
    {
        return getRealResources(getDefaultSun(), getDefaultYield());
    }

    /**
     * Returns the real cycle of this factory by including the sun power and the
     * asteroid yield into the calculation.
     * 
     * @param suns
     *            The sun power in percent
     * @param yield
     *            The asteroid yield
     * @return The production cycle
     */
    private int getRealCycle(final Sun suns, final int yield)
    {
        // Handle solar power plants
        if (isSolarPowerPlant()) return suns.getCycle();

        // Handle silicon mines
        if (isSiliconMine())
        {
            final int basetime = 2400 / (yield + 1) + 1;
            final int multiple = (int) Math.floor(59.9 / basetime) + 1;
            return basetime * multiple;
        }

        // Handle ore and ice mines
        if (isOreMine() || isIceMine())
        {
            final int basetime = 600 / (yield + 1) + 1;
            final int multiple = (int) Math.floor(59.9 / basetime) + 1;
            return basetime * multiple;
        }
        
        // Normal factories use normal cycle
        return this.cycle;
    }

    /**
     * Returns the real product of this factory by including the specified sun
     * power and asteroid yield into the calculation.
     * 
     * @param suns
     *            The sun power in percent
     * @param yield
     *            The asteroid yield
     * @return The product
     */
    private Product getRealProduct(final Sun suns, final int yield)
    {
        final Ware ware = this.product.getWare();

        // Handle silicon mines
        if (isSiliconMine())
        {
            final int baseTime = 2400 / (yield + 1) + 1;
            final int multiple = (int) Math.floor(59.9 / baseTime) + 1;
            return new Product(ware, multiple * this.product.getQuantity() / 2);
        }

        // Handle ore and ice mines
        if (isOreMine() || isIceMine())
        {
            final int baseTime = 600 / (yield + 1) + 1;
            final int multiple = (int) Math.floor(59.9 / baseTime) + 1;
            return new Product(ware, multiple
                * (this.product.getQuantity() / 4));
        }

        // Normal factory, return normal product
        return this.product;
    }

    /**
     * Returns the real resources of this factory by including the specified sun
     * power and asteroid yield into the calculation.
     * 
     * @param suns
     *            The sun power in percent
     * @param yield
     *            The asteroid yield
     * @return The resources
     */
    private Product[] getRealResources(final Sun suns, final int yield)
    {
        // Handle silicon mines
        if (isSiliconMine())
        {
            final Product resources[] = new Product[1];
            final Product resource = this.resources[0];
            final int baseTime = 2400 / (yield + 1) + 1;
            final int multiple = (int) Math.floor(59.9 / baseTime) + 1;
            resources[0] = new Product(resource.getWare(), multiple
                * resource.getQuantity() / 2);
            return resources;
        }

        // Handle ore and ice mines
        if (isOreMine() || isIceMine())
        {
            final Product resources[] = new Product[1];
            final Product resource = this.resources[0];
            final int baseTime = 600 / (yield + 1) + 1;
            final int multiple = (int) Math.floor(59.9 / baseTime) + 1;
            resources[0] = new Product(resource.getWare(), multiple
                * (resource.getQuantity() / 4));
            return resources;
        }

        // Normal factory, return normal product
        return this.resources;
    }

    /**
     * Returns the product this factory produces in one hour.
     * 
     * @param suns
     *            The sun power to use in the calculation (for solar power
     *            plants)
     * @param yield
     *            The yield to use in the calculation
     * @return The product per hour.
     */
    public final Product getProductPerHour(final Sun suns, final int yield)
    {
        final Product product = getRealProduct(suns, yield);
        return new Product(product.getWare(), product.getQuantity() * 60d * 60d
            / getRealCycle(suns, yield));
    }

    /**
     * Returns the product this factory produces in one hour for a default yield
     * (100% for power plants, 25 for mines).
     * 
     * @return The product produced per hour for a default yield
     */
    public Product getProductPerHour()
    {
        return getProductPerHour(this.game.getSunFactory().getDefaultSun(), 25);
    }

    /**
     * Returns the resources this factory needs in our hour.
     * 
     * @param suns
     *            The sun power to use in the calculation (for solar power
     *            plants)
     * @param yield
     *            The yield to use in the calculation (for mines)
     * @return The resources needed per hour
     */
    public Collection<Product> getResourcesPerHour(final Sun suns,
        final int yield)
    {
        final Product[] resources = getRealResources(suns, yield);
        final Collection<Product> resourcesPH = new ArrayList<Product>();
        for (final Product resource : resources)
        {
            resourcesPH.add(new Product(resource.getWare(), resource
                .getQuantity()
                * 60 * 60 / getRealCycle(suns, yield)));
        }
        return resourcesPH;
    }

    /**
     * Returns the resources this factory needs in one hour for a default yield
     * (100% for power plants, 25 for mines).
     * 
     * @return The resources needed per hour for a default yield
     */
    public Collection<Product> getResourcesPerHour()
    {
        return getResourcesPerHour(this.game.getSunFactory().getDefaultSun(),
            25);
    }

    /**
     * Returns the manufacturer sector which is nearest to the specified
     * sector.
     * 
     * @param sector
     *            The source sector
     * @return The nearest manufacturer sector
     */
    public Sector getNearestManufacturer(final Sector sector)
    {
        return getNearestManufacturer(sector, false);
    }

    /**
     * Returns the manufacturer sector which is nearest to the specified
     * sector.
     * 
     * @param sector
     *            The source sector
     * @param allRaces
     *            True to use shipyards of all races, false to ignore the
     *            shipyards of disabled races.
     * @return The nearest manufacturer sector
     */
    public Sector getNearestManufacturer(final Sector sector, final boolean
        allRaces)
    {
        final Config config = Config.getInstance(); 
        int distance = 0;
        Sector nearest = null;

        for (final Sector manufacturer : this.manufacturers)
        {
            final int curDistance = sector.getDistance(manufacturer);
            if (nearest == null || curDistance < distance && 
                (!config.isRaceIgnored(manufacturer.getRace()) || allRaces))
            {
                nearest = manufacturer;
                distance = curDistance;
            }
        }
        
        // When we did not find a manufacturer owned by an allowed race then
        // check again for all races.
        if (nearest == null && !allRaces)
            return getNearestManufacturer(sector, true);
        
        return nearest;
    }

    /**
     * @see de.ailis.xadrian.interfaces.GameProvider#getGame()
     */
    @Override
    public Game getGame()
    {
        return this.game;
    }
}
