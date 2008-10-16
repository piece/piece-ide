<?php
/* vim: set expandtab tabstop=4 shiftwidth=4: */

/**
 * PHP versions 4 and 5
 *
 * Copyright (c) 2007 Piece Project, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @package    Piece_Unity
 * @copyright  Piece Project
 * @license    http://www.opensource.org/licenses/bsd-license.php  BSD License (revised)
 * @version    SVN: $Id$
 * @since      File available since Release 1.0.0
 */

require_once 'Piece/Unity/Service/FlowAction.php';
require_once 'Piece/Unity/Service/FlexyElement.php';
require_once 'Piece/Unity/Service/Authentication.php';

// {{{ AuthenticationAction

/**
 * Action class for the flow AuthenticationAction.
 *
 * @package    Piece_Unity
 * @copyright  Piece Project
 * @license    http://www.opensource.org/licenses/bsd-license.php  BSD License (revised)
 * @version    Release: 1.1.0
 * @since      Class available since Release 1.0.0
 */
class AuthenticationAction extends Piece_Unity_Service_FlowAction
{

    // {{{ properties

    /**#@+
     * @access public
     */

    /**#@-*/

    /**#@+
     * @access private
     */

    var $_user;

    /**#@-*/

    /**#@+
     * @access public
     */

    function AuthenticationAction()
    {
        $this->_user = &new stdClass();
    }

    function doActivityOnProcessLogin()
    {
        $validation = &$this->_context->getValidation();
        if ($validation->validate('Authentication', $this->_user)) {
            if ($this->_user->loginName === 'guest' && $this->_user->password === 'guest') {
                $authentication = &new Piece_Unity_Service_Authentication();
                $authentication->login();
                if ($authentication->hasCallbackURL()) {
                    $authentication->redirectToCallbackURL();
                }

                return 'DisplayHomeFromProcessLogin';
            } else {
                $viewElement = &$this->_context->getViewElement();
                $viewElement->setElement('message', 'Login Failed!');
                return 'DisplayFormFromProcessLogin';
            }
        } else {
            return 'DisplayFormFromProcessLogin';
        }
    }

    function doActivityOnProcessLogout()
    {
        $authentication = &new Piece_Unity_Service_Authentication();
        $authentication->logout();
        return 'DisplayFinishFromProcessLogout';
    }

    function doActivityOnDisplayForm()
    {
        $flexyElement = &new Piece_Unity_Service_FlexyElement();
        $flexyElement->addForm($this->_flow->getView(), $this->_context->getScriptName());
        $flexyElement->restoreValues('Authentication', $this->_user);
        $flexyElement->setValue('password', '');

        $this->_setTitle();
    }

    function doActivityOnDisplayHome()
    {
        $viewElement = &$this->_context->getViewElement();
        $viewElement->setElement('user', $this->_user);

        $this->_setTitle();
    }

    function doActivityOnDisplayFinish()
    {
        $this->_setTitle();
    }

    /**#@-*/

    /**#@+
     * @access private
     */

    function _setTitle()
    {
        $viewElement = &$this->_context->getViewElement();
        $viewElement->setElement('title', 'B.1. An application component for authentication. *exclusive*');
    }

    /**#@-*/

    // }}}
}

// }}}

/*
 * Local Variables:
 * mode: php
 * coding: iso-8859-1
 * tab-width: 4
 * c-basic-offset: 4
 * c-hanging-comment-ender-p: nil
 * indent-tabs-mode: nil
 * End:
 */
?>
