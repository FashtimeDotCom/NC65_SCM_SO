package nc.pubitf.so.pub.api.rest;

import uap.ws.rest.resource.AbstractUAPRestResource;
import nc.vo.scmpub.res.Module;

/**
 * @description
 * 
 *
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-13 ����3:40:47
 * @author ����
 */
public class AbstractSORestResource {

  public String getModule() {
    return Module.SO.getName();
  }
}
