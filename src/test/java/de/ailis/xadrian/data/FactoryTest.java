/*
 * $Id$
 * Copyright (C) 2009 Klaus Reimer <k@ailis.de>
 * See LICENSE.TXT for licensing information
 */

package de.ailis.xadrian.data;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import de.ailis.xadrian.data.factories.FactoryFactory;
import de.ailis.xadrian.data.factories.RaceFactory;
import de.ailis.xadrian.data.factories.WareFactory;


/**
 * Tests the data of some factories.
 * 
 * @author Klaus Reimer (k@ailis.de)
 * @version $Revision$
 */

public class FactoryTest
{
    /** The factory factory */
    private static FactoryFactory factoryFactory = FactoryFactory.getInstance();

    /** The ware factory */
    private static WareFactory wareFactory = WareFactory.getInstance();

    /** The race factory */
    private static RaceFactory raceFactory = RaceFactory.getInstance();

    /** Energy cells */
    private static Ware energy = wareFactory.getWare("energyCells");

    /** Silicon wafers */
    private static Ware silicon = wareFactory.getWare("siliconWafers");

    /** Ore */
    private static Ware ore = wareFactory.getWare("ore");

    /** Crystals */
    private static Ware crystals = wareFactory.getWare("crystals");


    /**
     * Checks if the Teladi silicon mines are really the cheapest.
     */

    @Test
    public void testCheapestSiliconMines()
    {
        final Race teladi = raceFactory.getRace("teladi");
        assertEquals(teladi, factoryFactory.getCheapestFactory(silicon,
            FactorySize.M).getRace());
        assertEquals(teladi, factoryFactory.getCheapestFactory(silicon,
            FactorySize.L).getRace());
    }


    /**
     * Checks if the Split ore mines are really the cheapest.
     */

    @Test
    public void testCheapestOreMines()
    {
        final Race split = raceFactory.getRace("split");
        assertEquals(split, factoryFactory.getCheapestFactory(ore,
            FactorySize.M).getRace());
        assertEquals(split, factoryFactory.getCheapestFactory(ore,
            FactorySize.L).getRace());
    }


    /**
     * Checks if the Boron power plants are really the cheapest.
     */

    @Test
    public void testCheapestPowerPlants()
    {
        final Race boron = raceFactory.getRace("boron");
        assertEquals(boron, factoryFactory.getCheapestFactory(energy,
            FactorySize.M).getRace());
        assertEquals(boron, factoryFactory.getCheapestFactory(energy,
            FactorySize.L).getRace());
        assertEquals(boron, factoryFactory.getCheapestFactory(energy,
            FactorySize.XL).getRace());
    }
    

    /**
     * Tests the product and resources of the Silicon Mine M with yield 100.
     */

    @Test
    public void testSiliconMineM100()
    {
        final Factory mine = factoryFactory.getCheapestFactory(silicon,
            FactorySize.M);
        final Product product = mine.getProductPerHour(150, 100);
        assertEquals(silicon, product.getWare());
        assertEquals(300, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 100);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(7200, Math.round(resources.iterator().next().getQuantity()));
    }


    /**
     * Tests the product and resources of the Silicon Mine M with yield 25.
     */

    @Test
    public void testSiliconMineM25()
    {
        final Factory mine = factoryFactory.getCheapestFactory(silicon,
            FactorySize.M);
        final Product product = mine.getProductPerHour(150, 25);
        assertEquals(silicon, product.getWare());
        assertEquals(77, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 25);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(1858, Math.round(resources.iterator().next().getQuantity()));
    }

    
    /**
     * Tests the product and resources of the Silicon Mine M with yield 0.
     */

    @Test
    public void testSiliconMineM0()
    {
        final Factory mine = factoryFactory.getCheapestFactory(silicon,
            FactorySize.M);
        final Product product = mine.getProductPerHour(150, 0);
        assertEquals(silicon, product.getWare());
        assertEquals(3, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 0);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(72, Math.round(resources.iterator().next().getQuantity()));
    }


    /**
     * Tests the product and resources of the Silicon Mine L with yield 100.
     */

    @Test
    public void testSiliconMineL100()
    {
        final Factory mine = factoryFactory.getCheapestFactory(silicon,
            FactorySize.L);
        final Product product = mine.getProductPerHour(150, 100);
        assertEquals(silicon, product.getWare());
        assertEquals(750, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 100);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(18000, Math.round(resources.iterator().next().getQuantity()));
    }


    /**
     * Tests the product and resources of the Silicon Mine L with yield 25.
     */

    @Test
    public void testSiliconMineL25()
    {
        final Factory mine = factoryFactory.getCheapestFactory(silicon,
            FactorySize.L);
        final Product product = mine.getProductPerHour(150, 25);
        assertEquals(silicon, product.getWare());
        assertEquals(194, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 25);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(4645, Math.round(resources.iterator().next().getQuantity()));
    }

    
    /**
     * Tests the product and resources of the Silicon Mine L with yield 0.
     */

    @Test
    public void testSiliconMineL0()
    {
        final Factory mine = factoryFactory.getCheapestFactory(silicon,
            FactorySize.L);
        final Product product = mine.getProductPerHour(150, 0);
        assertEquals(silicon, product.getWare());
        assertEquals(7, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 0);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(180, Math.round(resources.iterator().next().getQuantity()));
    }
    
    
    /**
     * Tests the product and resources of the Ore Mine M with yield 100.
     */

    @Test
    public void testOreMineM100()
    {
        final Factory mine = factoryFactory.getCheapestFactory(ore,
            FactorySize.M);
        final Product product = mine.getProductPerHour(150, 100);
        assertEquals(ore, product.getWare());
        assertEquals(1200, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 100);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(7200, Math.round(resources.iterator().next().getQuantity()));
    }


    /**
     * Tests the product and resources of the Ore Mine M with yield 25.
     */

    @Test
    public void testOreMineM25()
    {
        final Factory mine = factoryFactory.getCheapestFactory(ore,
            FactorySize.M);
        final Product product = mine.getProductPerHour(150, 25);
        assertEquals(ore, product.getWare());
        assertEquals(300, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 25);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(1800, Math.round(resources.iterator().next().getQuantity()));
    }

    
    /**
     * Tests the product and resources of the Ore Mine M with yield 0.
     */

    @Test
    public void testOreMineM0()
    {
        final Factory mine = factoryFactory.getCheapestFactory(ore,
            FactorySize.M);
        final Product product = mine.getProductPerHour(150, 0);
        assertEquals(ore, product.getWare());
        assertEquals(12, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 0);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(72, Math.round(resources.iterator().next().getQuantity()));
    }


    /**
     * Tests the product and resources of the Ore Mine L with yield 100.
     */

    @Test
    public void testOreMineL100()
    {
        final Factory mine = factoryFactory.getCheapestFactory(ore,
            FactorySize.L);
        final Product product = mine.getProductPerHour(150, 100);
        assertEquals(ore, product.getWare());
        assertEquals(3000, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 100);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(18000, Math.round(resources.iterator().next().getQuantity()));
    }


    /**
     * Tests the product and resources of the Ore Mine L with yield 25.
     */

    @Test
    public void testOreMineL25()
    {
        final Factory mine = factoryFactory.getCheapestFactory(ore,
            FactorySize.L);
        final Product product = mine.getProductPerHour(150, 25);
        assertEquals(ore, product.getWare());
        assertEquals(750, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 25);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(4500, Math.round(resources.iterator().next().getQuantity()));
    }

    
    /**
     * Tests the product and resources of the Ore Mine L with yield 0.
     */

    @Test
    public void testOreMineL0()
    {
        final Factory mine = factoryFactory.getCheapestFactory(ore,
            FactorySize.L);
        final Product product = mine.getProductPerHour(150, 0);
        assertEquals(ore, product.getWare());
        assertEquals(30, Math.round(product.getQuantity()));
        final Collection<Product> resources = mine
            .getResourcesPerHour(150, 0);
        assertEquals(1, resources.size());
        assertEquals(energy, resources.iterator().next().getWare());
        assertEquals(180, Math.round(resources.iterator().next().getQuantity()));
    }
    
    
    /**
     * Tests the Solar Power Plant M with various suns.
     */

    @Test
    public void testSolarPowerPlantM()
    {
        Product product;
        Collection<Product> resources;
        final Factory plant = factoryFactory.getCheapestFactory(energy,
            FactorySize.M);

        // Test with suns 150%
        product = plant.getProductPerHour(150, 0);
        assertEquals(energy, product.getWare());
        assertEquals(18747, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(150, 0);
        assertEquals(1, resources.size());
        assertEquals(crystals, resources.iterator().next().getWare());
        assertEquals(136, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 100%
        product = plant.getProductPerHour(100, 0);
        assertEquals(16841, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(100, 0);
        assertEquals(122, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 0%
        product = plant.getProductPerHour(0, 0);
        assertEquals(13074, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(0, 0);
        assertEquals(95, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 300%
        product = plant.getProductPerHour(300, 0);
        assertEquals(22080, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(300, 0);
        assertEquals(160, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 400%
        product = plant.getProductPerHour(400, 0);
        assertEquals(22080, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(400, 0);
        assertEquals(160, Math.round(resources.iterator().next().getQuantity()));
    } 
    
    
    /**
     * Tests the Solar Power Plant L with various suns.
     */

    @Test
    public void testSolarPowerPlantL()
    {
        Product product;
        Collection<Product> resources;
        final Factory plant = factoryFactory.getCheapestFactory(energy,
            FactorySize.L);

        // Test with suns 150%
        product = plant.getProductPerHour(150, 0);
        assertEquals(energy, product.getWare());
        assertEquals(46868, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(150, 0);
        assertEquals(1, resources.size());
        assertEquals(crystals, resources.iterator().next().getWare());
        assertEquals(340, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 100%
        product = plant.getProductPerHour(100, 0);
        assertEquals(42102, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(100, 0);
        assertEquals(305, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 0%
        product = plant.getProductPerHour(0, 0);
        assertEquals(32684, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(0, 0);
        assertEquals(237, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 300%
        product = plant.getProductPerHour(300, 0);
        assertEquals(55200, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(300, 0);
        assertEquals(400, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 400%
        product = plant.getProductPerHour(400, 0);
        assertEquals(55200, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(400, 0);
        assertEquals(400, Math.round(resources.iterator().next().getQuantity()));
    }    
    
    
    /**
     * Tests the Solar Power Plant XL with various suns.
     */

    @Test
    public void testSolarPowerPlantXL()
    {
        Product product;
        Collection<Product> resources;
        final Factory plant = factoryFactory.getCheapestFactory(energy,
            FactorySize.XL);

        // Test with suns 150%
        product = plant.getProductPerHour(150, 0);
        assertEquals(energy, product.getWare());
        assertEquals(46868*2, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(150, 0);
        assertEquals(1, resources.size());
        assertEquals(crystals, resources.iterator().next().getWare());
        assertEquals(679, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 100%
        product = plant.getProductPerHour(100, 0);
        assertEquals(84203, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(100, 0);
        assertEquals(305*2, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 0%
        product = plant.getProductPerHour(0, 0);
        assertEquals(32684*2, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(0, 0);
        assertEquals(237*2, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 300%
        product = plant.getProductPerHour(300, 0);
        assertEquals(55200*2, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(300, 0);
        assertEquals(400*2, Math.round(resources.iterator().next().getQuantity()));

        // Test with suns 400%
        product = plant.getProductPerHour(400, 0);
        assertEquals(55200*2, Math.round(product.getQuantity()));
        resources = plant.getResourcesPerHour(400, 0);
        assertEquals(400*2, Math.round(resources.iterator().next().getQuantity()));
    }    
}
