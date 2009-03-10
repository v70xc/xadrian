<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>${complex.name}</title>
    <link rel="stylesheet" type="text/css" href="complex.css" />    
  </head>
  [#if print]
    [#assign class="print"]
  [#else]
    [#assign class="screen"]
  [/#if]  
  <body class="${class}">
    <h1>${complex.name}</h1>
    [#if complex.factories?size == 0]
      <p>[@message key="complex.noFactories" /]</p>
    [#else]
      <p>
        [@message key="complex.suns" />:
        [#if !print]<a href="file://changeSuns">[/#if]
        ${complex.suns}
        [#if !print]</a>[/#if]
      </p>
      <table class="complex">
        <tr>
          <th class="factory">[@message key="complex.factory" /]</th>
          <th class="race">[@message key="complex.race" /]</th>
          <th class="yield">[@message key="complex.yield" /]</th>
          <th class="quantity">[@message key="complex.quantity" /]</th>
          <th class="singlePrice">[@message key="complex.unitPrice" /]</th>
          <th class="price">[@message key="complex.price" /]</th>
          [#if !print]<td class="buttons"></td>[/#if]
        </tr>
        <tr>
          <td colspan="6"><hr /></td>
          [#if !print]<td><hr /></td>[/#if]
        </tr>
        [#list complex.factories as complexFactory]
          [#if complexFactory_index %2 == 0]
            [#assign class="even" /]
          [#else]
            [#assign class="odd" /]
          [/#if]
          [#assign factory=complexFactory.factory]
          <tr class="${class}">
            <td class="factory">${factory.name}</td>
            <td class="race">${factory.race.name}</td>
            <td class="yield">
              [#if complexFactory.factory.mine]
                [#if !print]<a href="file://changeYield/${complexFactory_index}">[/#if]
                  ${complexFactory.yield}
                [#if !print]</a>[/#if]
              [/#if]
            </td>
            <td class="quantity">
              [#if !complexFactory.factory.mine && !print]
                <a href="file://changeQuantity/${complexFactory_index}">${complexFactory.quantity}</a>
              [#else]
                ${complexFactory.quantity}
              [/#if]
            </td>
            <td class="singlePrice">${factory.price} Cr</td>
            <td class="price">${factory.price * complexFactory.quantity} Cr</td>
            [#if !print]
            <td>       
              <a href="file://removeFactory/${complexFactory_index}"><img src="../images/close.png" border="0" alt="" /></a>
            </td>
            [/#if]
          </tr>
        [/#list]
        [#if complex.autoFactories?size > 0]
          <tr class="sep"><td colspan="7"></td></tr>
          [#list complex.autoFactories as complexFactory]
            [#if (complex.factories?size + complexFactory_index) % 2 == 0]
              [#assign class="autoeven" /]
            [#else]
              [#assign class="autoodd" /]
            [/#if]
            [#assign factory=complexFactory.factory]
            <tr class="${class}">
              <td class="factory">${factory.name}</td>
              <td class="race">${factory.race.name}</td>
              <td class="yield">
                [#if complexFactory.factory.mine]
                  ${complexFactory.yield}
                [/#if]
              </td>
              <td class="quantity">${complexFactory.quantity}</td>
              <td class="singlePrice">${factory.price} Cr</td>
              <td class="price">${factory.price * complexFactory.quantity} Cr</td>
              [#if !print]
              <td>       
                <a href="file://acceptFactory/${complexFactory_index}"><img src="../images/add.png" border="0" alt="" /></a>
              </td>
              [/#if]
            </tr>
          [/#list]
        [/#if]
        [#if complex.kitQuantity > 0]
          [#if (complex.autoFactories?size + complex.factories?size) % 2 == 0]
            [#assign class="kitseven" /]
          [#else]
            [#assign class="kitsodd" /]
          [/#if]
          <tr class="sep"><td colspan="7"></td></tr>
          <tr class="${class}">
            <td colspan="3" class="factory">[@message key="complex.kit" /]</td>
            <td class="quantity">${complex.kitQuantity}</td>
            <td class="singlePrice">${complex.kitPrice} Cr</td>
            <td class="price">${complex.totalKitPrice} Cr</td>
            [#if !print]<td></td>[/#if]
          </tr>
        [/#if]
        <tr>
          <td colspan="6"><hr /></td>
          [#if !print]<td><hr /></td>[/#if]
        </tr>
        <tr>
          <th colspan="3">[@message key="complex.total" /]</th>
          <td class="quantity">
            ${complex.totalQuantity}
            [#if complex.kitQuantity > 0]
            (+${complex.kitQuantity} [@message key="complex.kits" /])
            [/#if]
          </td>
          <td></td>
          <td class="price">${complex.totalPrice} Cr</td>
          [#if !print]<td></td>[/#if]
        </tr>
      </table>
    [/#if]
    [#if !print]
    <p>
      <a href="file://addFactory">[@message key="complex.addFactory" /]</a>
    </p>
    [/#if]
    [#if complex.factories?size > 0]      
      <h2>[@message key="complex.productionStats" /]</h2>
      <table>
        <tr>
          <th class="ware">[@message key="complex.ware" /]</th>
          <th class="units">[@message key="complex.produced" /]</th>
          <th class="units">[@message key="complex.needed" /]</th>
          <th class="units">[@message key="complex.surplus" /]</th>
          <th class="price">[@message key="complex.buyPrice" /]</th>
          <th class="price">[@message key="complex.sellPrice" /]</th>
          <th class="profit">[@message key="complex.profit" /]</th>
        </tr>
        <tr>
          <td colspan="4"><hr /></td>
          <td colspan="3"><hr /></td>
        </tr>
        [#list complex.wares as complexWare]
          [#if complexWare_index %2 == 0]
            [#assign class="even" /]
          [#else]
            [#assign class="odd" /]
          [/#if]
          <tr class="${class}">
            <td class="ware">${complexWare.ware.name}</td>
            <td class="units">${complexWare.produced?round}</td>
            <td class="units">${complexWare.needed?round}</td>
            [#assign surplus = complexWare.produced - complexWare.needed]
            [#if surplus > 0]
              [#assign class="surplus"]
            [#elseif surplus lt 0]
              [#assign class="missing"]
            [#else]
              [#assign class="balanced"]
            [/#if]
            <td class="${class}">${surplus?round}</td>
            <td class="price">
              [#if surplus lt 0]
                ${complexWare.buyPrice} Cr
              [#else]
                -
              [/#if]
            </td>          
            <td class="price">
              [#if surplus > 0]
                ${complexWare.sellPrice} Cr
              [#else]
                -
              [/#if]
            </td>          
            <td class="profit">${complexWare.profit?round} Cr</td>
          </tr>
        [/#list]
        <tr>
          <td colspan="4"><hr /></td>
          <td colspan="3"><hr /></td>
        </tr>
        <tr>
          <th class="profit" colspan="6">[@message key="complex.total" /]</th>
          <td class="profit">${complex.profit?round} Cr</td>          
        </tr>        
      </table>

      <h2>[@message key="complex.storageCapacities" /]</h2>
      <table>
        <tr>
          <th class="ware">[@message key="complex.ware" /]</th>
          <th class="container">[@message key="complex.container" /]</th>
          <th class="volume">[@message key="complex.wareVolume" /]</th>
          <th class="units">[@message key="complex.storageUnits" /]</th>
          <th class="volume">[@message key="complex.storageVolume" /]</th>
        </tr>
        <tr>
          <td colspan="5"><hr /></td>
        </tr>
        [#list complex.capacities as capacity]
          [#if capacity_index %2 == 0]
            [#assign class="even" /]
          [#else]
            [#assign class="odd" /]
          [/#if]
          <tr class="${class}">
            <td class="ware">${capacity.ware.name}</td>
            <td class="container">${capacity.ware.container}</td>
            <td class="volume">${capacity.ware.volume}</td>
            <td class="units">${capacity.quantity}</td>
            <td class="volume">${capacity.volume}</td>
          </tr>
        [/#list]
        <tr>
          <td colspan="5"><hr /></td>
        </tr>
        <tr>
          <th class="ware">[@message key="complex.total" /]</th>
          <td></td>          
          <td></td>          
          <td class="profit">${complex.totalCapacity}</td>
          <td class="volume">${complex.totalStorageVolume}</td>          
        </tr>        
      </table>
    [/#if]
  </body>
</html>